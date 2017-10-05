package ca.ubc.ece.cpen221.mp3.staff;

/**
 * This class models a vertex of the graph. DO NOT MODIFY THIS FILE.
 */
public class Vertex {

	private String label;

	/**
	 * Create a new vertex with a given label
	 * 
	 * @param label
	 *            with which to identify the vertex
	 */
	public Vertex(String label) {
		this.label = label;
	}

	/**
	 * Obtain the label associated with a vertex
	 * 
	 * @return label associated with this vertex
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the label for a vertex
	 * 
	 * @param label
	 *            to set for the vertex
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Check equality of vertices. This method overrides equals( ) in Object.
	 * 
	 * @return true if this vertex is equal to the obj otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex) obj;
		return label.equals(other.label);
	}

	/**
	 * For fast equality checking. This method overrides hashCode() in Object.
	 * 
	 * @return a hash code for this vertex
	 */
	@Override
	public int hashCode() {
		return label.hashCode();
	}

	/**
	 * Obtain a string representation of the vertex
	 * 
	 * @return the label associated with the vertex as its string
	 *         representation.
	 */
	@Override
	public String toString() {
		return label;
	}
}
