package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;

@State(Scope.Benchmark)
public class PutBenchmark {

  protected final int spansSize = 1000000;
  protected final int traceSpansSize = 1000;
  protected final int tracesSize = 1000000;
  protected static final Random RANDOM = new Random(System.currentTimeMillis());
  protected final Vector<String> spans = new Vector<>();

  @Benchmark
  @Fork(value = 2)
  @Warmup(iterations = 1)
  @Measurement(iterations = 3)
  @Threads(value = 1)
  @BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
  public void Test_LinkedHashSet() {
    Set<String> uniqueSpans = new LinkedHashSet<>();

    for (int i = 0; i < spansSize; i++) {
      spans.add("span" + i);
    }

    // Testing the speed of adding a LinkedHashSet.
    // Each trace could have maximum 1000 spans.
    for (int i = 0; i < tracesSize; i++) {
      for (int j = 0; j < traceSpansSize; j++) {
        int index = RANDOM.nextInt(spansSize);
        uniqueSpans.add(spans.get(index));
      }
    }
  }

  @Benchmark
  @Fork(value = 2)
  @Warmup(iterations = 1)
  @Measurement(iterations = 3)
  @Threads(value = 1)
  @BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
  public void Test_HashSet() {
    Set<String> uniqueSpans = new HashSet<>();

    for (int i = 0; i < spansSize; i++) {
      spans.add("span" + i);
    }

    // Testing the speed of adding a HashSet.
    // Each trace could have maximum 1000 spans.
    for (int i = 0; i < tracesSize; i++) {
      for (int j = 0; j < traceSpansSize; j++) {
        int index = RANDOM.nextInt(spansSize);
        uniqueSpans.add(spans.get(index));
      }
    }
  }
}