# Finger Exercise: Data Structures & Algorithm

![Gradle CI](https://github.com/jeffreywangcf/data-structures/actions/workflows/gradle.yml/badge.svg)
![Coverage](.github/badges/jacoco.svg)

My implementation of common data structures and algorithms in Java. Tested correctness and efficiency with JUnit 5.

## Algorithms

### [Sorter](src/main/java/datastructures/algo/Sort.java)

- selection sort
- insertion sort
- merge sort
- merge sort bottom up
- quick sort (2-way partition)
- heap sort

### [Graph Traversal](src/main/java/datastructures/algo/Search.java)

- BFS
- DFS

### Shortest Path (coming soon)

- Dijkstra
- Bellman Ford

### Minimum Spanning Tree (coming soon)

- Prim
- Kruskal

## Data Structures

- dynamic array (coming soon)
- [deque / doubly linked list](src/main/java/datastructures/ds/DoublyEndedQueue.java)
- [priority queue](src/main/java/datastructures/ds/PriorityQueue.java)
- hash table (coming soon)

### Trees

- [BST](src/main/java/datastructures/ds/tree/Dictionary.java)
- [AVL Tree](src/main/java/datastructures/ds/tree/SelfBalancingDictionary.java)
- B-tree (coming soon)
- trie (coming soon)
- huffman tree (coming soon)

### Graphs

- [unweighted graph w/ adjacent list](src/main/java/datastructures/ds/graph/SparseGraph.java)
- [unweighted graph w/ adjacent matrix](src/main/java/datastructures/ds/graph/DenseGraph.java)
- [weighted graph w/ adjacent list](src/main/java/datastructures/ds/graph/WeightedGraph.java)
- [disjoint set / union find](src/main/java/datastructures/ds/DisjointSet.java)
