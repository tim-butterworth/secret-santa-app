alert('hi');

1 0
2 2
3 0
4 1
5 4
6 2
7 2
8 10
9 4
10 6
11 


  0    1
a {a}  {b}
b {a,c}  \
c \    {a,b}

    0        1        10110
{a} 0 -> {a} 1 -> {b} 1 -> \

    0        1        0          1          0           1           1          0
{a} 0 -> {a} 1 -> {b} 0 -> {a,c} 1 -> {a,b} 0 -> {a, c} 1 -> {b, a} 1 -> {\,b} 0 -> {c}

{0,1}*{0,1}{0,1}*
