public class Enemy {
    String name;
    float health;
    int attack;
    int defence;
    int guarding =0;
    Enemy(String name, float health, int attack, int defence){
        this.name =name;
        this.health= health;
        this.attack= attack;
        this.defence= defence;
    }
}

class bat extends Enemy{
    bat(){super("bat",15,21,2);}
}

class wolf extends Enemy{
    wolf(){super("wolf",20,30,4);}
}

class bear extends Enemy{
    bear(){super("bear",40,40,20);}
}

class bandit extends Enemy{
    bandit(){super("bandit",25,35,10);}
}