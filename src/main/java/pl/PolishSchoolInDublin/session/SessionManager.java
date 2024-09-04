package pl.PolishSchoolInDublin.session;

public class SessionManager {

    private static SessionManager instance;     //singleton easy way for global session

    private boolean fruitSelected;
    private boolean animalSelected;
    private boolean tailsSelected;

    private SessionManager() {
        fruitSelected = false;
        animalSelected = false;
        tailsSelected = false;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public boolean isFruitSelected() {
        return fruitSelected;
    }

    public void setFruitSelected(boolean fruitSelected) {
        this.fruitSelected = fruitSelected;
        this.animalSelected = !fruitSelected;
        this.tailsSelected = !fruitSelected;
    }

    public boolean isAnimalSelected() {
        return animalSelected;

    }

    public void setAnimalSelected(boolean animalSelected) {
        this.animalSelected = animalSelected;
        this.fruitSelected = !animalSelected;
        this.tailsSelected = !animalSelected;
    }

    public boolean isTailsSelected() {
        return tailsSelected;
    }

    public void setTailsSelected(boolean tailsSelected) {
        this.tailsSelected = tailsSelected;
        this.fruitSelected = !tailsSelected;
        this.animalSelected = !tailsSelected;
    }
}