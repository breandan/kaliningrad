package edu.mcgill.kaliningraph

import guru.nidi.graphviz.model.MutableGraph
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Edge.DEFAULT_LABEL
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class TranslationTest {
  val randomGraph = LabeledGraph().prefAttach(vertices = 10)

  @Test
  fun testTinkerpopTranslationInvariance() =
    randomGraph.let { assertEquals(it, it.toTinkerpop().toKaliningraph()) }

  @Test
  fun testJGraphTTranslationInvariance() =
    randomGraph.let { assertEquals(it, it.toJGraphT().toKaliningraph()) }

  @Test
  fun testGraphvizTranslationInvariance() =
    randomGraph.let { assertEquals(it, it.toGraphviz().toKaliningraph()) }

  fun MutableGraph.toKaliningraph() =
    LabeledGraphBuilder { edges().forEach { LGVertex(it.from()?.name()?.value()) - LGVertex(it.to().name().value()) } }

  fun Graph<*, *, *>.toJGraphT() =
    SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java).apply {
      vertices.forEach { addVertex(it.id) }
      edgList.forEach { (source, edge) -> addEdge(source.id, edge.target.id) }
    }

  fun <E> org.jgrapht.Graph<String, E>.toKaliningraph() =
    LabeledGraphBuilder { edgeSet().map { e -> LGVertex(getEdgeSource(e)) - LGVertex(getEdgeTarget(e)) } }

  fun Graph<*, *, *>.toTinkerpop(): GraphTraversalSource =
    TinkerGraph.open().traversal().apply {
      val map = vertices.map { it to addV(it.id).next() }.toMap()
      edgList.forEach { (v, e) ->
        (map[v] to map[e.target])
          .also { (t, s) -> addE(if (e is LabeledEdge) e.label ?: DEFAULT_LABEL else DEFAULT_LABEL).from(s).to(t).next() }
      }
    }

  fun GraphTraversalSource.toKaliningraph() =
    LabeledGraphBuilder { E().toList().forEach { LGVertex(it.inVertex().label()) - LGVertex(it.outVertex().label()) } }
}