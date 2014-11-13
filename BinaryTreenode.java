public class BinaryTreenode<E> {

	private E data;
	private BinaryTreenode<E> left, right;
	
	public BinaryTreenode() {
		this.data = null;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTreenode(E data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	public E getData() {
		return data;
	}
	
	public BinaryTreenode<E> getLeft() {
		return left;
	}
	
	public BinaryTreenode<E> getRight() {
		return right;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public void setLeft(E left) {
		this.left = new BinaryTreenode<E>(left);
	}
	
	public void setRight(E right) {
		this.right = new BinaryTreenode<E>(right);
	}
}
