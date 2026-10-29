# Presonus Atom SQ Bitwig Controller Extension (2026 Enhancement by LQdbMusic based on James Owesome original Code, engineered bei Claude Code)

Copy the bwextension file here
- macOS: Documents/Bitwig Studio/Extensions
- Windows: %USERPROFILE%\Documents\Bitwig Studio\Extensions
- Linux: ~/Bitwig Studio/Extensions
  
The manual is included in the zip file. Copy this wherever you will find it again. 
 
## Brief Description

This controller extension provides many functions above and beyond the basic Midi Mode implementation. It is written in Java in order to leverage certain advanced features of the Bitwig API that are not available in Javascript, primarily the hardware abstraction. A few key features:
1. Endless Encoders!
2. Several Modes, based on the menu buttons on the side of the display, each with it's own mapping of the display buttons and encoders.
3. Lots of Track and Device controls, such as preset browsing, adding, deleting and moving tracks or devices.
4. Functional and dynamic use of the display. Track, Device and Preset names are shown and updated automagically.
5. Retains native Midi-mode hardware control of the keyboard settings (keyboard mode, Range, Oct, Scale Root, Velocity).

## Enhancements 2026
1. Editor = Session View / Clip View, 8 Scenes x 8 Clips Rows
2. in Session View 
-LEDs represent Clip-Color,
-Cursor Buttons navigate Scene / Clip Frame
-press Clip-Pad start Clip, SHIFT+Clip-Pad stops Clip
-Display: start Scenes 1-4, 5-8
3. Device Remote Parameter (Inst) now shows in Display while tweaking for 1.5sec

## Bitwig API 
This extension was written using API version 17. but works with current Bitwig 6.x

## Bitwig Java Framework
This extension leverages the framework components (for the controller Layers) provided by Bitwig in their Github repo here: 
https://github.com/bitwig/bitwig-extensions/tree/api-17/src/main/java/com/bitwig/extensions/framework
These are covered by an MIT license, which is included for reference in the relevant folder in this repo. 
## AS-IS

**This code is provided with absolutely no warranty!** I can confirm it has not bricked my Atom SQ, or caused any issues with my Bitwig installation. I cannot foresee any potential issues, but you know, just in case, use at your own risk. 

## Feedback
If you have comments on this script, feel free to let me know. I can be reached via Discord: Nova#4280
