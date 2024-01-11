
Mathematical Model

M: very large number
N: number of orders
Nb: number of box types
Indices
4. i: Index of Order
5. j: Index of Box
Parameters
4. li: Largest edge of order i
5. mi: Medium edge of order i
6. si: Smallest edge of order i
Decision Variables
24. Lj: Largest edge of box j
25. Mj: Medium edge of box j
26. Sj: Smallest edge of box j
27. oij={1, if order i is in box j
 0, otherwise

OBJ=minimum ∑MSu=1 S1u u


Subject to
xi + pilxi + qiwxi + rihxi ≤ xk + (1-aik)M , ∀ i,k ∈ N [1]
xk + pklxi + qklxk +rkhxk ≤ xi + (1-bik)M , ∀ i,k ∈ N [2]
yi + pilyi + qiwyi + rihyi ≤ yk + (1-cik)M , ∀ i,k ∈ N [3]
yk + pklyk + qkwyk + rkhyk ≤ yi + (1-dik)M , ∀ i,k ∈ N [4]
zi + pilzi + qiwzi + rihzi ≤ zk + (1-eik)M , ∀ i,k ∈ N [5]
zk + pklzk + qkwzk + rkhzk ≤ zi + (1-fik)M , ∀ i,k ∈ N [6]
aik + bik + cik + dik + eik + fik ≥ 1 , ∀ i,k ∈ N [7]
all items in an order should be at least in left tor right or back or front or below or above of any other item)
lxi + wxi + hxi = 1 , ∀ i ∈ N [8]
(at least one edge should be parallel to x axis)
lyi + wyi + hyi = 1 , ∀ i ∈ N [9]
lzi + wzi + hzi = 1 , ∀ i ∈ N [10]
lxi + lyi + lzi = 1 , ∀ i ∈ N [11]
(length should be parallel to one of the axis)
wxi + wyi + wzi = 1 , ∀ i ∈ N [12]
hxi + hyi + hzi = 1 , ∀ i ∈ N [13]
∑MSu=1 Wmaxu = 1 [14]
(if width of the order is u then, Wmaxu is 1)
∑MSu=1 Hmaxu = 1 [15]
∑MSu=1 Lmaxu = 1 [16]
xi + pilxi + qiwxi + rihxi ≤ ∑MSu=1 Lmaxuu , ∀ i ∈ N [17]
yi + pilyi + qiwyi + rihyi ≤ ∑MSu=1 Wmaxuu , ∀ i ∈ N [18]
zi + pilzi + qiwzi + rihzi ≤ ∑MSu=1 Hmaxuu , ∀ i ∈ N [19]
∑MSu=1 B1u ≥ ∑MSu=1 Lmaxuu [20]
B1u ≤ MWmaxu , ∀ u ∈ MS [21]
∑MSu=1 S1u ≥ ∑MSu=1 B1u u [22]
S1u ≤ MHmaxu , ∀ u ∈ MS [23]
Su ≥ 0 , B1u ≥ 0 , ∀ u ∈ MS [24]
xi , yi , zi ≥ 0 , ∀ i ∈ N [25]

Performance Of The Algorithm:

Max Problem Size:

 Execution time: 673.1554326 seconds 1000 orders 100 boxes (problem size of 100000 binary variables and
300 continuous variables)

Optimality Gap With Smaller Problem Sizes:

 Based on 15 sample problems which contains
10 orders and 3 box we have obtained 11 solutions with approximately %0,0001 or less optimality gap, 1 solution with %1.4
optimality gap, 2 solutions with %5.2 optimality gap and one solution with %14 optimality gap
