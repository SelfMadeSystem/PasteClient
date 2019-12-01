/*
 * Copyright 2019 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.Paste.Utils;

public class TimeUtils {

    public static String formatTime(long l) {
        long minutes = l / 1000 / 60;

        l -= minutes * 1000 * 60;

        long seconds = l / 1000;

        l -= seconds * 1000;

        StringBuilder sb = new StringBuilder();

        if (minutes != 0) sb.append(minutes).append("min ");
        if (seconds != 0) sb.append(seconds).append("s ");

        if (l != 0 || minutes == 0 && seconds == 0) {
            sb.append(l).append("ms ");
        }

        return sb.substring(0, sb.length() - 1);
    }
}
