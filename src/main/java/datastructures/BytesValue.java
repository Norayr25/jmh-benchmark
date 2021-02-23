package datastructures;

import java.util.Arrays;

import static com.apple.foundationdb.tuple.ByteArrayUtil.printable;

/** wrap byte[] so they are comparable by content */
public class BytesValue {

  protected volatile byte[] bytes;

  public BytesValue(byte[] bytes) {
    this.bytes = bytes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BytesValue)) return false;

    BytesValue that = (BytesValue) o;
    return Arrays.equals(bytes, that.bytes);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(bytes);
  }

  @Override
  public String toString() {
    return printable(getBytes());
  }

  public byte[] getBytes() {
    return bytes;
  }
}
