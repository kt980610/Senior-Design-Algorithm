
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

Lj ≥ li – M(1-oij) ∀ i ∈ [1,N] , ∀ j ∈ [1,Nb] [1]

Mj ≥ mi – M(1-oij) ∀ i ∈ [1,N] , ∀ j ∈ [1,Nb] [2]

Sj ≥ si – M(1-oij) ∀ i ∈ [1,N] , ∀ j ∈ [1,Nb] [3]

∑Nbj=1 oij = 1 ∀ i ∈ [1,N] [4] 

Performance Of The Algorithm:

Max Problem Size:

 Execution time: 673.1554326 seconds 1000 orders 100 boxes (problem size of 100000 binary variables and
300 continuous variables)

Optimality Gap With Smaller Problem Sizes:

 Based on 15 sample problems which contains
10 orders and 3 box we have obtained 11 solutions with approximately %0,0001 or less optimality gap, 1 solution with %1.4
optimality gap, 2 solutions with %5.2 optimality gap and one solution with %14 optimality gap
