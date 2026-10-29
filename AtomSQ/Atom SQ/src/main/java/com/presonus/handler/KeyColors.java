// Colour output for the Atom SQ keyboard LEDs (used by the Editor / Session View mode).
// Licensed under GPLv3 - https://www.gnu.org/licenses/gpl-3.0.txt
package com.presonus.handler;

import com.bitwig.extension.controller.api.MidiOut;

/**
 * Sends RGB colours to the keyboard key LEDs of the Atom SQ in native mode.
 *
 * Protocol (confirmed on a real Atom SQ, same scheme as the original ATOM, works with the
 * extension's own native mode handshake):
 *  - Note-On, channel 2 / 3 / 4 (0-indexed 1 / 2 / 3), velocity 0-127 sets the R / G / B value of a key
 *  - Note-On, channel 1 (0-indexed 0), velocity 127 switches the key LED on, velocity 0 switches it off
 *  - the 32 keys send / light notes 36 (lowest key) to 67 (highest key)
 */
public class KeyColors
{
    /** Note number of the lowest key (measured). */
    public static final int FIRST_KEY_NOTE = 36;

    /** Number of keys on the Atom SQ keyboard (measured: lowest key 36, highest key 67). */
    public static final int KEY_COUNT = 32;

    private final MidiOut midiOut;

    //last colour sent per key, -1 = unknown / off. Only changes are sent.
    private final int[] lastR = new int[KEY_COUNT];
    private final int[] lastG = new int[KEY_COUNT];
    private final int[] lastB = new int[KEY_COUNT];

    public KeyColors(final MidiOut midiOut)
    {
        this.midiOut = midiOut;
        invalidate();
    }

    /** Sets one key (index 0-31) to the colour; components 0.0 - 1.0. Black switches the LED off. */
    public void setKey(final int index, final double red, final double green, final double blue)
    {
        if (index < 0 || index >= KEY_COUNT)
        {
            return;
        }
        final int r = toMidi(red);
        final int g = toMidi(green);
        final int b = toMidi(blue);

        if (r == lastR[index] && g == lastG[index] && b == lastB[index])
        {
            return;
        }

        final int note = FIRST_KEY_NOTE + index;
        if (r == 0 && g == 0 && b == 0)
        {
            midiOut.sendMidi(0x90, note, 0);
        }
        else
        {
            midiOut.sendMidi(0x91, note, r);
            midiOut.sendMidi(0x92, note, g);
            midiOut.sendMidi(0x93, note, b);
            midiOut.sendMidi(0x90, note, 127);
        }
        lastR[index] = r;
        lastG[index] = g;
        lastB[index] = b;
    }

    /** Switches all key LEDs off. */
    public void clear()
    {
        for (int i = 0; i < KEY_COUNT; i++)
        {
            setKey(i, 0, 0, 0);
        }
    }

    /** Forget what was sent, e.g. after the controller was re-initialised. */
    public void invalidate()
    {
        for (int i = 0; i < KEY_COUNT; i++)
        {
            lastR[i] = -1;
            lastG[i] = -1;
            lastB[i] = -1;
        }
    }

    private static int toMidi(final double value)
    {
        final double v = Math.max(0.0, Math.min(1.0, value));
        return (int) Math.round(v * 127.0);
    }
}
