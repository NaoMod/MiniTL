package org.tetrabox.example.minitl.runtime.lrp;

import org.tetrabox.example.minitl.runtime.serializers.EitherSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Union type.
 *
 * @param <L> First possible type.
 * @param <R> Second possible type.
 */
@JsonSerialize(using = EitherSerializer.class)
public class Either<L, R> {

	public static <L, R> Either<L, R> forLeft(L left) {
		return new Either<>(left, null);
	}

	public static <L, R> Either<L, R> forRight(R right) {
		return new Either<>(null, right);
	}

	private L left;
	private R right;

	private Either(L left, R right) {
		super();
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	public boolean isLeft() {
		return left != null;
	}

	public boolean isRight() {
		return right != null;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("Either [").append(System.lineSeparator());
		builder.append("  left = ").append(left).append(System.lineSeparator());
		builder.append("  right = ").append(right).append(System.lineSeparator());
		return builder.append("]").toString();
	}
}