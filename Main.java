import java.util.Scanner;
import java.util.Random;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        System.out.println("=====Monster Adventure Game=====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Welcome " + name + "!");

        Character player = new Character(name, 100);

        int potions = 2;
        int gold = 0;

        boolean playing = true;

        while (playing && !player.isDead()) {
            line();
            System.out.println("1. Explore Forest");
            System.out.println("2. Visit Village");
            System.out.println("3. Rest");
            System.out.println("4. View Stats");
            System.out.println("5. Quit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    forestAdventure(player);
                    break;
                case 2:
                    gold += village(player, potions);
                    break;
                case 3:
                    rest(player);
                    break;
                case 4:
                    System.out.println(player.getStats());
                    System.out.println("Potions: " + potions);
                    System.out.println("Gold: " + gold);
                    break;
                case 5:
                    playing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            if (player.kills >= 5) {
                bossBattle(player);
                playing = false;
            }
        }

        if (player.isDead()) {
            System.out.println("\nYou died...");
        }

        System.out.println("GAME OVER");
    }

    public static void forestAdventure(Character player) {
        line();
        System.out.println("You enter the dark forest...what will you do?");
        System.out.println("1. Follow strange noises");
        System.out.println("2. Search for treasure");
        System.out.println("3. Enter a cave");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                fightRandomMonster(player);
                break;
            case 2:
                treasureEvent(player);
                break;
            case 3:
                caveEvent(player);
                break;
            default:
                System.out.println("You got lost in the forest.");
        }
    }

    public static void fightRandomMonster(Character player) {
        Monster monster;

        int randomMonster = rand.nextInt(3);

        if (randomMonster == 0) {
            monster = new Monster("Goblin", 40, "Earth");
        } else if (randomMonster == 1) {
            monster = new Monster("Skeleton", 50, "Undead");
        } else {
            monster = new Monster("Wolf", 45, "Beast");
        }

        System.out.println("\nA " + monster.name + " attacks!");

        while (!monster.isDead() && !player.isDead()) {

            System.out.println("\n1. Attack");
            System.out.println("2. Run");

            int choice = scanner.nextInt();

            if (choice == 1) {
                int playerDamage = rand.nextInt(20) + 5;
                monster.lostLife(playerDamage);
                System.out.println("You hit for " + playerDamage);

                if (!monster.isDead()) {
                    int monsterDamage = rand.nextInt(15) + 5;
                    player.lostLife(monsterDamage);
                    System.out.println(monster.name + " attacks for " + monsterDamage);
                }

            } else if (choice == 2) {
                int escape = rand.nextInt(100);

                if (escape < 50) { //random chance to escape
                    System.out.println("You escaped!");
                    return;
                } else {
                    System.out.println("Escape failed!");
                }
            }
        }

        if (monster.isDead()) {

            System.out.println("\nYou defeated the " + monster.name);

            player.gainKill();
            player.levelUp();

            System.out.println("You levelled up!");
            System.out.println("Level: " + player.level);
        }
    }

    public static void treasureEvent(Character player) {
        line();
        System.out.println("You find an old treasure chest. What will you do?");
        System.out.println("1. Open it");
        System.out.println("2. Ignore it");

        int choice = scanner.nextInt();

        if (choice == 1) {
            int trap = rand.nextInt(100);
            if (trap < 40) {
                System.out.println("It was trapped!");
                player.lostLife(20);
            }
            else {
                System.out.println("You found gold and a potion!");
            }

        }
        else {
            System.out.println("You walk away safely.");
        }
    }

    public static void caveEvent(Character player) {
        line();
        System.out.println("Inside the cave you see glowing eyes...what will you do?");
        System.out.println("1. Fight");
        System.out.println("2. Sneak away");

        int choice = scanner.nextInt();

        if (choice == 1) {
            Monster caveMonster = new Monster("Cave Troll", 70, "Rock");
            battle(player, caveMonster);

        }
        else {
            int sneak = rand.nextInt(100);
            if (sneak < 60) {
                System.out.println("You escaped quietly.");
            } else {
                System.out.println("The monster spotted you!");
                fightRandomMonster(player);
            }
        }
    }

    public static void battle(Character player, Monster monster) {
        System.out.println("\nBattle against " + monster.name);

        while (!monster.isDead() && !player.isDead()) {
            System.out.println("\n1. Attack");
            System.out.println("2. Defend");

            int choice = scanner.nextInt();

            if (choice == 1) {
                int damage = rand.nextInt(25) + 5;
                monster.lostLife(damage);
                System.out.println("You deal " + damage + " damage.");
            }
            else {
                System.out.println("You defend yourself.");
            }
            if (!monster.isDead()) {
                int monsterDamage = rand.nextInt(20) + 5;
                player.lostLife(monsterDamage);
                System.out.println(monster.name + " attacks for " + monsterDamage);
            }
        }

        if (monster.isDead()) {
            System.out.println("Monster defeated!");

            player.gainKill();
            player.levelUp();
        }
    }

    public static int village(Character player, int potions) {
        line();
        System.out.println("You arrive at the village. What will you do?");
        System.out.println("1. Talk to merchant");
        System.out.println("2. Talk to guard");
        System.out.println("3. Leave");

        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Merchant gives you 20 gold.");
            return 20;
        }
        else if (choice == 2) {
            System.out.println("Guard warns about a Dragon nearby.");
        }
        else {
            System.out.println("You leave the village.");
        }
        return 0;
    }

    public static void rest(Character player) {
        int life = rand.nextInt(20) + 10;
        player.gainLife(life);
        System.out.println("You rest at a campfire.");
        System.out.println("Recovered and gained " + life + " lives.");
    }

    public static void bossBattle(Character player) {
        line();
        System.out.println("FINAL BOSS UNLOCKED!");
        System.out.println("The Dragon King appears!");
        Monster boss = new Monster("Dragon King", 150, "Fire");

        battle(player, boss);

        if (!player.isDead()) {
            System.out.println("\nYOU SAVED THE KINGDOM!");
        }
    }

    //line for each new part
    public static void line() {
        System.out.println("\n==========================");
    }
}
