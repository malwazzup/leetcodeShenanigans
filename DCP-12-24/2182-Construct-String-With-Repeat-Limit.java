
class Solution {
    public static String repeatLimitedString(String s, int repeatLimit) {
        // Count the frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Create a priority queue to store characters by descending order
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                maxHeap.add((char) (i + 'a'));
            }
        }

        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            // Take the largest character
            char current = maxHeap.poll();

            // Determine how many times we can use this character consecutively
            int count = Math.min(freq[current - 'a'], repeatLimit);

            // Add the character `count` times to the result
            for (int i = 0; i < count; i++) {
                result.append(current);
            }
            freq[current - 'a'] -= count;

            if (freq[current - 'a'] > 0) {
                // If there are more characters remaining, add the next largest one
                if (!maxHeap.isEmpty()) {
                    char next = maxHeap.poll();

                    // Add one instance of the next largest character to break the streak
                    result.append(next);
                    freq[next - 'a']--;

                    if (freq[next - 'a'] > 0) {
                        maxHeap.add(next);
                    }

                    // Re-add the current character back to the heap
                    maxHeap.add(current);
                } else {
                    // If there are no more characters to use, break
                    break;
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(repeatLimitedString("cczazcc", 3)); // Output: "zzcccac"
        System.out.println(repeatLimitedString("aababab", 2)); // Output: "bbabaa"
    }
}
