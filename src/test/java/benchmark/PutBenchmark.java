package benchmark;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.wavefront.sdk.common.Pair;
import datastructures.BytesKey;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class PutBenchmark {
  protected static final Random RANDOM = new Random(System.currentTimeMillis());
  protected static final int BYTES_COUNT = 8;
  protected static final int DUPLICATES_COUNT = 5;

  private final Cache<BytesKey, String> putCache =
          Caffeine.newBuilder().maximumSize(1_000_000).expireAfterWrite(1, TimeUnit.DAYS).build();
  public final ConcurrentMap<BytesKey, String> putCacheMap = putCache.asMap();

  private final Cache<BytesKey, String> putIfCache =
      Caffeine.newBuilder().maximumSize(1_000_000).expireAfterWrite(1, TimeUnit.DAYS).build();
  public final ConcurrentMap<BytesKey, String> putIfCacheMap = putIfCache.asMap();

  private final Cache<BytesKey, String> computeCache =
          Caffeine.newBuilder().maximumSize(1_000_000).expireAfterWrite(1, TimeUnit.DAYS).build();
  public final ConcurrentMap<BytesKey, String> computeCacheMap = computeCache.asMap();

  @Benchmark
  @Fork(value = 20)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1)
  @Threads(value = 20)
  @BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
  public void putTest() {
    byte[] bytes = new byte[BYTES_COUNT];
    Set<Pair<BytesKey, String>> dataSet = new HashSet();
    for (int j = 0; j < 10000; j++) {
      RANDOM.nextBytes(bytes);
      BytesKey tempKey = new BytesKey(bytes);
      dataSet.add(new Pair<>(tempKey, tempKey.toString()));
    }

    for (int n = 0; n < DUPLICATES_COUNT; n++) {
      for (Pair<BytesKey, String> pair : dataSet) {
        putCacheMap.put(pair._1, pair._2);
      }
    }
  }

  @Benchmark
  @Fork(value = 20)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1)
  @Threads(value = 20)
  @BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
  public void putIfTest() {
    byte[] bytes = new byte[BYTES_COUNT];
    Set<Pair<BytesKey, String>> dataSet = new HashSet();
    for (int j = 0; j < 10000; j++) {
      RANDOM.nextBytes(bytes);
      BytesKey tempKey = new BytesKey(bytes);
      dataSet.add(new Pair<>(tempKey, tempKey.toString()));
    }

    for (int n = 0; n < DUPLICATES_COUNT; n++) {
      for (Pair<BytesKey, String> pair : dataSet) {
        putIfCacheMap.putIfAbsent(pair._1, pair._2);
      }
    }
  }

  @Benchmark
  @Fork(value = 20)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1)
  @Threads(value = 20)
  @BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
  public void computeTest() {
    byte[] bytes = new byte[BYTES_COUNT];
    Set<Pair<BytesKey, String>> dataSet = new HashSet();
    for (int j = 0; j < 10000; j++) {
      RANDOM.nextBytes(bytes);
      BytesKey tempKey = new BytesKey(bytes);
      dataSet.add(new Pair<>(tempKey, tempKey.toString()));
    }

    for (int n = 0; n < DUPLICATES_COUNT; n++) {
      for (Pair<BytesKey, String> pair : dataSet) {
        computeCacheMap.computeIfAbsent(pair._1, key -> pair._2);
      }
    }
  }
}
