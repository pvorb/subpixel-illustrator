subpixel-illustrator
====================

This is a small Scala program that lets you define a screenshot image and a
factor and resizes the image by that factor, making use of a typical screen's
pixel composition.

![Screenshot](https://raw.github.com/pvorb/subpixel-illustrator/master/src/test/resources/screenshot.png)


Download
--------

You can download the
[runnable JAR file](https://vorb.de/dev/downloads/subpixel-illustrator.jar).


Requirements
------------

  * Java Runtime Environment (JRE) Version 6 or newer


Usage
-----

 1. First, make a screenshot of some text and save it as a PNG file

    ![Example input](https://raw.github.com/pvorb/subpixel-illustrator/master/src/test/resources/example-in.png)

 2. Open the program by double clicking on the JAR file
 3. Click on “Choose source image ...”
 4. Adjust the scale factor (optional; per default, each pixel will result in a
    9 × 9 square)
 5. Hit “Scale!” and choose a destination for the resulting PNG image.

Here's the result of our example

![Example output](https://raw.github.com/pvorb/subpixel-illustrator/master/src/test/resources/example-out.png)


License
-------

Copyright © 2012 Paul Vorbach

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the “Software”), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
