package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 161117
Write a program that outputs the string representation of numbers from 1 to n.
But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:
n = 15,
Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */
public class P412_FizzBuzz {

	/**
	 * AC: 4ms, 42%
	 */
	public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
			int a = i % 3;
			int b = i % 5;
			if(a != 0 && b != 0)
				list.add(i + "");
			else if(a == 0 && b != 0)
				list.add("Fizz");
			else if(a != 0 && b == 0)
				list.add("Buzz");
			else
				list.add("FizzBuzz");
		}
        return list;
    }
	
	public static void main(String[] args) {
		P412_FizzBuzz p = new P412_FizzBuzz();
		System.out.println(p.fizzBuzz(15));
	}

}
