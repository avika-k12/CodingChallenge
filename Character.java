public class Character {
    public String name;
    public int lives;
    public int kills;
    public int level;

    public Character(String name, int lives){
        this.name = name;
        this.lives = lives;
        this.kills = 0;
        this.level = 1;
    }

    public void lostLife(int damage){
        lives -= damage;

        if (lives < 0 ){
            lives = 0;
        }
    }

    public void gainLife(int amount){
        lives += amount;

        if (lives > 100){
            lives = 100;
        }
    }

    public void gainKill(){
        kills++;
    }

    public void levelUp(){
        level++;
        System.out.println(name + " levelled up!");
    }

    public boolean isDead(){
        return lives <= 0;
    }

    public String getStats(){
        return "\nPlayer: " + name + "\nLives: " + lives + "\nKills: " + kills + "\nLevel: " + level;
    }

    public int attack(){
        return (int)(Math.random() * 20) + 5;
    }
}
