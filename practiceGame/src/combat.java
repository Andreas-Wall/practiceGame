

import  java.util.Scanner;
import  java.util.Random;



public class combat {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //turn will allow the game to decide which side to assign priority to
        //1 = player, 2 = enemy
        int turn = 1;
        Random ran = new Random();
        float attackChance;
        int damage;
        int run;
        float enemyHealthPerc;
        int enemyChoice  = 0;
        //used to decide if enemy will attack or guard
        int enemyOR;
        String[] enemies={"bat","wolf","bear","bandit"};
        String encounter = enemies[ran.nextInt(4)];

        //calls in player class
        Player ply = new Player();
        //calls in enemy class
        Class<?> cls = Class.forName(encounter);
        Enemy eny = (Enemy) cls.newInstance();

        //will take user input
        Scanner sc = new Scanner(System.in);

        //takes players choice
        int choice;

        System.out.println("You have encountered " + eny.name + "!");

        //while loop used to keep the game going till the player or enemy is killed
        while(ply.health >0 & eny.health >0){
            //if turn is set to 1 then the game will prompt the player to make their choice.
            //the 3 choice will for now are fight, guard, or run
            //fight will attack the enemy
            //guard will set the defence of the player to 2 * player.defence
            //run will have the player try to leave combat immediately, this has a 50% chance
            if(turn == 1){
                //if the player was guarding last turn this will reset the defence of the player
                if(ply.guarding == 1){
                    ply.defence = ply.defence /2;
                    ply.guarding = 0;
                }

                System.out.println("Players turn!\n");

                System.out.println("1: Fight");
                System.out.println("2: Guard");
                System.out.println("3: Run");

                choice = sc.nextInt();

                //switch set up to activate players choice
                switch (choice){
                    case 1:
                        //calculate damage chances
                        attackChance = ran.nextInt(100) + 1;
                        //calculate damage
                        if(attackChance > ((1.0f/32) * 100)){
                            damage = (ply.attack - eny.defence) / 2;
                            if(damage < 0){
                                damage = 0;
                            }
                            System.out.println("Player has done " + damage + " damage!\n");
                            eny.health = eny.health - damage;
                            if (eny.health <=0){
                                System.out.println("Congrats!!! You win");
                                System.out.println("Thank you for playing!");
                                System.exit(0);
                            }
                        }
                        break;
                    case 2:
                        //sets guarding to 1 to show the player is guarding and needs to be reset
                        //increases the players defence by half
                        ply.guarding = 1;
                        ply.defence = ply.defence *2;
                        break;
                    case 3:
                        //This gives the player a 50/50 chance of leaving the battle
                        //If the player succeeds in leaving it will thank them and exit out of the game
                        run = ran.nextInt(100)+1;
                        if (run >= 50){
                            System.out.println("You have fled the battle.");
                            System.out.println("Thank you for playing!");
                            System.exit(0);
                        }
                        else System.out.println("You are unsuccessful at fleeing the battle.");
                        break;
                }//end of switch
                turn = 2;
            }//end of turn mechanic
            //----------------------------------------------------------------------------------------------------------------------------------------
            //if turn is set to 2 then the computer will play for the enemy
            if(turn == 2){
                //if the enemy was guarding last turn this will reset the defence of the enemy
                if(eny.guarding == 1){
                    eny.defence = ply.defence /2;
                    eny.guarding = 0;
                }

                System.out.println("Enemy's turn!\n");
                //Depending on the health the enemy will decide to fight or stay
                //as along as the enemy is above 50% health the enemy will attack
                //if the enemy's health is lower than 50% but above 15% the enemy will decide to guard or attack
                //at 15% or less the enemy will try to leave
                enemyHealthPerc = 0.5f * eny.health;
                if(eny.health > enemyHealthPerc){
                    enemyChoice =1;
                }
                else if (eny.health < enemyHealthPerc & eny.health > (0.15f * eny.health)){
                    enemyOR = ran.nextInt(100)+1;
                    if(enemyOR>50)
                        enemyChoice =1;
                    else enemyChoice = 2;
                }
                else{
                    enemyChoice = 3;
                }

                //switch set up to activate enemy choice
                switch (enemyChoice){
                    case 1:
                        //calculate damage chances
                        attackChance = ran.nextInt(100) + 1;
                        //calculate damage
                        if(attackChance > ((1.0f/32) * 100)){
                            damage = (eny.attack - ply.defence) / 2;
                            if(damage < 0){
                                damage = 0;
                            }
                            System.out.println(eny.name + " has done " + damage + " damage!\n");
                            ply.health = ply.health - damage;
                            if (ply.health <=0){
                                System.out.println("You have died.");
                                System.out.println("Thank you for playing!");
                                System.exit(0);
                            }
                        }
                        break;
                    case 2:
                        //sets guarding to 1 to show the enemy is guarding and needs to be reset
                        //increases the enemy defence by half
                        eny.guarding = 1;
                        eny.defence = eny.defence *2;
                        break;
                    case 3:
                        //This gives the enemy a 50/50 chance of leaving the battle
                        //If the enemy succeeds in leaving it will thank the player and exit out of the game
                        run = ran.nextInt(100)+1;
                        if (run >= 50){
                            System.out.println("The enemy has fled the battle.");
                            System.out.println("Thank you for playing!");
                            System.exit(0);
                        }
                        else System.out.println("The enemy was unsuccessful at fleeing the battle.");
                        break;
                }//end of switch
                turn = 1;
            }//end of turn mechanic
        }//end of while ----- end of game
    }//end of main
}//end of program
