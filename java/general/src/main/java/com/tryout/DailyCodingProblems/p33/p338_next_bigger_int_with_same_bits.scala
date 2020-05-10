package com.tryout.DailyCodingProblems.p33

// Given an integer n, find the next biggest integer with the same number of 1-bits on. For example, given the number 6 (0110 in binary), return 9 (1001).
object p338_next_bigger_int_with_same_bits {
  // 0000 -> 0000
  //0001 -> 0010
  //0010 -> 0100
  //0011 -> 0101
  //0111 -> 1011
  //0110 -> 1001
  //1010 -> 1100
  //1011 -> 1101
  //1101 -> 1110
  //0010 1011 -> 0010 1101
  // def next_integer_binary(num):
  //    if num == 0:
  //        return 0
  //
  //    count = 0  # The number of 1-bits to clear
  //    mask = 1
  //    # Iterate from LSB to MSB until we get a 0 followed by 1
  //    while not ((num & mask) > 0 and (num & (mask << 1)) == 0):
  //        if num & mask > 0:
  //            count += 1    # Count the number of set bits
  //            num &= ~mask  # The set the bit to 0
  //        mask <<= 1
  //
  //    num &= ~mask      # Set the bit to 0
  //    num |= mask << 1  # Set the next highest bit to 1
  //
  //    # Set the appropriate number of bits to 1 starting from LSB
  //    for i in range(count):
  //        num |= 1 << i
  //
  //    return num
}
