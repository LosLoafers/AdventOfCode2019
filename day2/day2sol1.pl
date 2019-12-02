#!/usr/bin/perl

use strict;
use warnings;
use Data::Dumper;

open(my $fh, "<", "input.txt")
	or die "Can't open file input.txt";
my @code = <$fh> =~ /(\d+)/g;
@code[1..2] = (12,2);
my $i = 0;
print("Input: @code\n");
while ($i <= $#code) {
  if ($code[$i] == 99) {
    print("Done, the value at position 0 is:" . $code[0] . "\n");
   # print("The entire opcode is now:\n @code");
    exit;
  }
  elsif ($code[$i] !~ /^(1|2)$/) {
    print("$code[$i] is not a valid instruction.");
    exit;
  }
  else {
    $code[$code[$i+3]] = op($code[$i],$code[$code[$i+1]],$code[$code[$i+2]],$code[$i+3]);
    print("@code\n");
  }
  $i = $i + 4
}

close($fh);

sub op {
  my ($opcode,$term1,$term2,$term3) = @_;
  print("opcode: $opcode, term1: $term1, term2: $term2, res: $term3\n");
  return $opcode == 1 ? $term1 + $term2 : $term1 * $term2;
}
