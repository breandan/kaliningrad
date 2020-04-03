# Kaliningraph

![](https://upload.wikimedia.org/wikipedia/commons/1/15/Image-Koenigsberg%2C_Map_by_Merian-Erben_1652.jpg)

# Graphs, Inductively

What are graphs? A graph is a (possibly empty) set of nodes.

What are nodes? A node is a unique integer with neighbors (possibly containing itself).

What are neighbors? Neighbors are a graph.

# Requirements

Kaliningraph uses [KraphViz](https://github.com/nidi3/graphviz-java#kotlin-dsl) for graph visualization.

# Getting Started

Run [the demo](src/main/kotlin/edu/mcgill/HelloKaliningraph.kt) via `./gradlew HelloKaliningraph` to get started.

# References

* [Solutio problematis ad geometriam situs pertinentis](http://eulerarchive.maa.org/docs/originals/E053.pdf), Leonard Euler
* [Think Like a Vertex, Behave Like a Function! A Functional DSL for Vertex-Centric Big Graph Processing](http://research.nii.ac.jp/~hu/pub/icfp16.pdf), Kento Emoto et al.
* [Inductive Graphs and Functional Graph Algorithms](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.28.9377&rep=rep1&type=pdf), Martin Erwig
* [Functional programming with structured graphs](http://www.cs.utexas.edu/~wcook/Drafts/2012/graphs.pdf), Bruno Oliveira and William Cook