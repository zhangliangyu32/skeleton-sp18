/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = asciis[i];
        }
        int max = 0;
        for (String s : asciis) {
            max = max > s.length() ? max : s.length();
        }
        for (int d = max; d >= 0; d--) {
            sorted = sortHelperLSD(sorted, d);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[257];
        for (String s : asciis) {
            if (s.length() <= index) {
                counts[0] += 1;
            }
            else {
                counts[(int) (s.charAt(index)) + 1] += 1;
            }
        }
        int[] starts = new int[257];
        int pos = 0;
        for (int i = 0; i < starts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        int place = 0;
        for (int i = 0; i < sorted.length; i++) {
            String item = asciis[i];
            if (item.length() <= index) {
                place = starts[0];
                sorted[place] = item;
                starts[0] += 1;
            }
           else {
               place = starts[(int)(item.charAt(index)) + 1];
               sorted[place] = item;
               starts[(int)(item.charAt(index)) + 1] += 1;
            }
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
