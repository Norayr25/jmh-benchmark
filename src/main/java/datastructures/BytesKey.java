package datastructures;



import com.google.common.primitives.UnsignedBytes;
import com.wavefront.sdk.common.Pair;

import java.util.Comparator;

/**
 * Created by ____ on ____.
 */
public class BytesKey extends BytesValue implements Comparable<BytesKey> {

    public static final Comparator<byte[]> COMPARATOR = UnsignedBytes.lexicographicalComparator();

    // compare only the byte[], and comparing with lexicographical comparator
    public static final Comparator<Pair<?, byte[]>> PAIR_COMPARATOR =
            (o1, o2) -> BytesKey.COMPARATOR.compare(o1._2, o2._2);

    private final int hashCode;

    public BytesKey(byte[] bytes) {
        super(bytes);
        this.hashCode = super.hashCode();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public int compareTo(BytesKey o) {
        return COMPARATOR.compare(this.bytes, o.bytes);
    }
}