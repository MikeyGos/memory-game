package pl.PolishSchoolInDublin.session;

public class SessionManager {

    private static SessionManager instance;

    private boolean fruitSelected;
    private boolean animalSelected;


    private SessionManager() {
        fruitSelected = false;
        animalSelected = false;
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
    }

    public boolean isAnimalSelected() {
        return animalSelected;
    }

    public void setAnimalSelected(boolean animalSelected) {
        this.animalSelected = animalSelected;
        this.fruitSelected = !animalSelected;
    }
}

