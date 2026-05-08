public class Monster extends Character {
    public String monsterType;

    public Monster(String name, int lives, String type) {
        super(name, lives);
        this.monsterType = type;
    }

    @Override
    public String getStats(){
        return "\nMonster: " + name + "\nType: " + monsterType + "\nLives: " + lives;
    }

    @Override
    public int attack(){
        return (int)(Math.random() * 15) + 10;
    }
}

