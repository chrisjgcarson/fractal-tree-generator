# Interactive Fractal Tree Generator

A Java desktop app that draws recursive fractal trees and updates them in real time
as you adjust the settings.

## Features
- Sliders for recursion depth (4–20), left and right branch angles, child-to-parent
  length ratio, trunk length, and trunk width
- Color pickers for the trunk and leaves, with colors blending smoothly from trunk to tips
- Branches that get thinner at each level
- A Random button that generates a new tree with random settings
- Renders up to ~1 million branches at depth 20 (each level doubles the branch count)

## How it works
Each branch draws itself, then recursively creates two smaller child branches at the
chosen angles until it reaches the set depth.

The app uses the Observer design pattern: the fractal generator holds the tree data,
and the drawing panel listens for changes. When any setting changes, the generator
notifies the panel, which redraws the tree. Branches are only regenerated when the
settings or window size change.

## How to run
Run `Main.java`. Two windows open: one shows the tree and the other has the controls.

## Built with
Java, Swing, AWT

*Project for CSC 143, North Seattle College.*
