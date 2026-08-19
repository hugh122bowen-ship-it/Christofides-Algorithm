package CA;


import java.util.Random;
/**
 * Generates a given number of cities at random positions in a given map size
 */
public class CityGenerator {
	private final int mapSize;
	private City[] cities;
	private int cityCount;
	public CityGenerator(int cityCount, int mapSize, int randomSeed) {
		this.cityCount = cityCount;
		this.mapSize = mapSize;
		cities = generateCities(randomSeed);
	}
	
	
	/**
	 * @return An array of City classes with randomly generated coordinates in a given area
	 */
	public City[] generateCities(int randomSeed) {
		City[] cities = new City[cityCount];
		Random random = new Random(randomSeed);
		for(int i = 0; i < cityCount; i++) {
			int generatedXPos = random.nextInt(mapSize);
			int generatedYPos = random.nextInt(mapSize);
			City generatedCity = new City(generatedXPos, generatedYPos);
			cities[i] = generatedCity;
		}
		return cities;
	}
	
	/**
	 * Generates a complete weighted graph, each edge is the pairwise distance between the two vertices it is connected to
	 * @return
	 */
	public GraphMatrix generatePairwiseGraph() {
		GraphMatrix pairwiseGraph = new GraphMatrix(cityCount);
		for(int y = 0; y < cityCount; y++) {
			for(int x = y+1; x < cityCount; x++) {
				double pairwiseDistance = findDistanceBetweenCities(cities[x], cities[y]);
				pairwiseGraph.addEdge(x, y, pairwiseDistance);
			}
		}
		return pairwiseGraph;
	}
	
	
	private double findDistanceBetweenCities(City c1, City c2) {
		double pairwiseDistance = Math.sqrt(Math.pow(c2.getPosX()-c1.getPosX(), 2) + Math.pow(c2.getPosY() - c1.getPosY(), 2));
		return pairwiseDistance;
		
	}
	
	public City[] getCities() {
		return this.cities;
	}
}
