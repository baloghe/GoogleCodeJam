package util;

/**
 * Solves a single case (given raw inputs only)
 *
 */
public interface CaseSolver {
	
	/**
	 * solves the given case
	 * @param inCase raw inputs as read from file
	 * @return solution to be written out into the submission file (except 'Case i#' prefix)
	 */
	public String solveCase(RawInput inCase);
	
}
