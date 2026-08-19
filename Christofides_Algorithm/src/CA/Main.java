package CA;
public class Main {
	public static void main(String[] args) {
		final int vertexCount = validateVertexCount(500);
		final int mapSize = validateMapSize(650);
		final int randomSeed = 100;
		
		CityGenerator cityGenerator = new CityGenerator(vertexCount, mapSize, randomSeed);
		GraphMatrix pairwiseGraph = cityGenerator.generatePairwiseGraph();
		
		ChristofidesAlgorithm christofidesAlgorithm = new ChristofidesAlgorithm(vertexCount);
		GraphList tspTour = christofidesAlgorithm.generateTour(pairwiseGraph);
		
		TSPLowerBound tspLowerBoundComputor = new TSPLowerBound();
		double tspLowerBound = tspLowerBoundComputor.findTSPLowerBound(pairwiseGraph, vertexCount);
		
		GraphicsManager graphicsManager = new GraphicsManager();
		graphicsManager.showImage(graphicsManager.generateGraphImage(cityGenerator.getCities(), tspTour, mapSize, tspLowerBound));
		
	}
	
	
	public static int validateVertexCount(int _vertexCount) {
		if(_vertexCount < 3) {
			return 3;
		}else {
			return _vertexCount;
		}
	}
	
	public static int validateMapSize(int _mapSize) {
		if(_mapSize < 350) {
			return 350;
		}else {
			return _mapSize;
		}
	}

}
