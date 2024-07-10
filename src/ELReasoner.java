
import java.util.*;

public class ELReasoner {
    private Set<String> concepts;
    private Set<String> individuals;
    private Map<String, Set<String>> conceptHierarchy;
    private Map<String, Set<String>> roleHierarchy;
    private Map<String, Set<String>> instanceOfConcepts;
    private Map<String, Set<String>> hasChildRoles;

    public ELReasoner() {
        concepts = new HashSet<>();
        individuals = new HashSet<>();
        conceptHierarchy = new HashMap<>();
        roleHierarchy = new HashMap<>();
        instanceOfConcepts = new HashMap<>();
        hasChildRoles = new HashMap<>();
    }

    // Add a concept to the hierarchy
    public void addConcept(String parent, String child) {
        concepts.add(parent);
        concepts.add(child);
        conceptHierarchy.computeIfAbsent(child, k -> new HashSet<>()).add(parent);
        System.out.println("conceptHierarchy"+conceptHierarchy);
    }

    // Add a role to the hierarchy
    public void addRole(String parent, String child) {
        roleHierarchy.computeIfAbsent(child, k -> new HashSet<>()).add(parent);
        System.out.println("roleHierarchy"+roleHierarchy);
    }

    // Add an individual as an instance of a concept
    public void addInstanceOfConcept(String individual, String concept) {
        individuals.add(individual);
        instanceOfConcepts.computeIfAbsent(individual, k -> new HashSet<>()).add(concept);
        System.out.println("instanceOfConcepts"+instanceOfConcepts);
    }

    // Add a relationship between two individuals
    public void addHasChild(String parent, String child) {
        hasChildRoles.computeIfAbsent(parent, k -> new HashSet<>()).add(child);
        System.out.println("hasChildRoles"+hasChildRoles);
    }

    // Perform classification (compute subsumption hierarchy)
    public void classify() {
        // Simplified example: just print the hierarchy
        System.out.println("Concept Hierarchy:");
        for (String child : conceptHierarchy.keySet()) {
            for (String parent : conceptHierarchy.get(child)) {
                System.out.println(child + " ⊑ " + parent);
            }
        }

        System.out.println("\nRole Hierarchy:");
        for (String child : roleHierarchy.keySet()) {
            for (String parent : roleHierarchy.get(child)) {
                System.out.println(child + " ⊑ " + parent);
            }
        }
    }

    // Check instances for concept
    public void checkInstances() {
        System.out.println("\nInstance Checking:");
        for (String individual : instanceOfConcepts.keySet()) {
            for (String concept : instanceOfConcepts.get(individual)) {
                System.out.println(individual + " ∈ " + concept);
            }
        }
    }

    // Perform reasoning and propagation
    public void propagate() {
        // Simplified example: propagate parenthood
        System.out.println("\nPropagation:");
        for (String parent : hasChildRoles.keySet()) {
            for (String child : hasChildRoles.get(parent)) {
                System.out.println(parent + " hasChild " + child);
                if (instanceOfConcepts.containsKey(parent) && instanceOfConcepts.get(parent).contains("Human")) {
                    System.out.println(parent + " ∈ Parent");
                }
            }
        }
    }

    public static void main(String[] args) {
        ELReasoner reasoner = new ELReasoner();

        // Adding concepts and roles
        reasoner.addConcept("Animal", "Human");
        reasoner.addConcept("Human", "Parent");
        reasoner.addConcept("Parent", "Father");
        reasoner.addRole("hasOffspring", "hasChild");

        // Adding individuals and their relationships
        reasoner.addInstanceOfConcept("John", "Human");
        reasoner.addInstanceOfConcept("Mary", "Human");
        reasoner.addInstanceOfConcept("John", "Father");


        reasoner.addHasChild("John", "Mary");

        // Reasoning steps
        reasoner.classify();
        reasoner.checkInstances();
         reasoner.propagate();
    }
}
