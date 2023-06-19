package Game;
import DataStructures.DoublyLinkedList;

import java.lang.Integer;

/**
* Clase para crear un nuevo Juego
*/
public class Game
{
    public Player player;
    Integer points;
    Integer vel;
    DoublyLinkedList<Kremlin> kremlins;
    DoublyLinkedList<Fruit> fruits;
    DoublyLinkedList<Vine> vines;
    /**
	* Constructor de juego
	*/
    public Game()
    {
        points = 0;
        vel = 5;
        player = new Player();
        vines =  new DoublyLinkedList<>();
        kremlins =  new DoublyLinkedList<>();
        fruits =  new DoublyLinkedList<>();
        generate_vines();
    }
    /**
	* Función para mover al jugador (W hacia arriba, S hacia abajo, A hacia la izquierda y D hacia la derecha)
	* @param command dircción de movimiento
	*/
    public void move_player(String command)
    {
        Vine actual_vine = getVine(player.vine);
        if(command == "w")//up
        {
            if(player.y >= actual_vine.getPosicion()[1])
            {
                player.move_up();
            }           

        }
        else if(command == "s")//down
        {
            if(player.y <= actual_vine.getPosicion()[2])
            {
                player.move_down();
            }
            
        }
        else if(command == "a")//left
        {
            Vine temp = getVine(player.vine -1);
            if(((actual_vine.getId()>1) && (player.getY()>= temp.getPosicion()[1])
                                        && (player.getY()< temp.getPosicion()[2]))
                                        || (player.getY()<=145))
            {
                player.move_sideways(temp.getPosicion()[0], temp.getId());

            }

            
        }
        else if(command == "d")//right
        {
            Vine temp = getVine(player.vine +1);
            if((actual_vine.getId()<10) && (player.getY()>= temp.getPosicion()[1])
                                         && (player.getY()< temp.getPosicion()[2]))
                                         
            {
                player.move_sideways(temp.getPosicion()[0], temp.getId());

            }
            
        }
    }
    /**
	* Funcion para crear una fruta
	* @param vine numero de vine al que se quiere crear
	* @param y posicion en la vine
	*/
    public void generate_fruit(Integer vine, Integer y)
    {
        Vine temp= getVine(vine);
        Integer y_inicial = temp.getPosicion()[1];
        Integer y_final = temp.getPosicion()[2];
        Integer newY =  y_inicial + ((y*(y_final - y_inicial))/100);
        Integer pos_y = (newY/10)*10;
        Fruit fruit = new Fruit(temp.getPosicion()[0],pos_y);
        fruits.add(fruit);
    }
     /**
	* Funcion para crear un enemigo
    * @param type 1 si es enemigo rojo y 2 si es azul
	* @param vine numero de vine al que se quiere crear
	* @param y posicion en la vine
	*/
    public void generate_Kremlin(String type, Integer vine, Integer y)
    {
        if(type.equals("1"))
        {
            generate_redKremlin(vine, y);
            System.out.println("Creando cocodrilo rojo");
        }
        if(type.equals("2"))
        {
            generate_blueKremlin(vine, y);
            System.out.println("Creando cocodrilo azul");
        }
    }
     /**
	* Funcion para crear un enemigo rojo
	* @param vine numero de vine al que se quiere crear
	* @param y posicion en la vine
	*/
    public void generate_redKremlin(Integer vine, Integer y)
    {
        Vine temp= getVine(vine);
        Integer initial_y = temp.getPosicion()[1];
        Integer final_y = temp.getPosicion()[2];
        Integer newY =  initial_y + ((y*(final_y - initial_y))/100);
        Integer pos_y = (newY/10)*10;
        
        RedKremlin redKremlin = new RedKremlin(temp.getPosicion()[0],pos_y,temp.getPosicion());
        redKremlin.setSpeed(vel);
        kremlins.add(redKremlin);

       
    }
    /**
	* Funcion para crear un enemigo azul
	* @param vine numero de vine al que se quiere crear
	* @param y posicion en la vine
	*/
    public void generate_blueKremlin(Integer vine, Integer y)
    {
        Vine temp= getVine(vine);
        Integer y_inicial = temp.getPosicion()[1];
        Integer y_final = temp.getPosicion()[2];
        Integer newY =  y_inicial + ((y*(y_final - y_inicial))/100);
        Integer pos_y = (newY/10)*10;

        BlueKremlin blueKremlin = new BlueKremlin(temp.getPosicion()[0],pos_y,temp.getPosicion());
        blueKremlin.setSpeed(vel);
        kremlins.add(blueKremlin);
    }
    /**
	* Funcion para eliminar un enemigo
    * @param type 1 si es enemigo rojo y 2 si es azul
	* @param vine numero de vine al que se quiere crear
	*/
    public void eliminate_Kremlin(String type, Integer vine)
    {
        
        Vine temp= getVine(vine);
        String posicion = type + "," + temp.getPosicion()[0];
        for(int i = 0; i< kremlins.size(); i++)
        {
            String id = kremlins.get(i).getPosition();
            String newID = id.split(",")[0] + "," + id.split(",")[1];
           
            if(newID.equals(posicion))
            {
                kremlins.removeAt(i);
                System.out.println("coco eliminado");
                break;
            }
            
        }

    }
    /**
	* Funcion para eliminar una fruta
	* @param vine numero de vine al que se quiere crear
	* @param y en la vine
	*/
    public void eliminate_fruit(Integer vine, Integer y)
    {
        
        Vine temp= getVine(vine);
        Integer y_inicial = temp.getPosicion()[1];
        Integer y_final = temp.getPosicion()[2];
        Integer newY = y_inicial + ((y*(y_final - y_inicial))/100);
        Integer pos_y = (newY/10)*10;
        String position = temp.getPosicion()[0] + "," + pos_y;
        for(int i = 0; i< fruits.size(); i++)
        {
            String id = fruits.get(i).getPosition();
            
            if(id.equals(position))
            {
                fruits.removeAt(i);
                System.out.println("fruta eliminado");
                break;
            }
            
        }

    }
    /**
	* Funcion para crear las 10 vines del juego
	*/
    public void generate_vines()
    {
        
        Vine vine1 = new Vine(1, 85, 230, 585);
        Vine vine2 = new Vine(2, 205, 230, 585);
        Vine vine3 = new Vine(3, 325, 360, 600);
        Vine vine4 = new Vine(4, 461, 230, 490);
        Vine vine5 = new Vine(5, 575, 230, 525);
        Vine vine6 = new Vine(6, 690, 230, 420);
        Vine vine7 = new Vine(7, 813, 255, 510);
        Vine vine8 = new Vine(8, 935, 255, 465);
        Vine vine9 = new Vine(9, 1055, 140, 510);
        Vine vine10 = new Vine(10, 1178, 140, 510);

        this.vines.add(vine1);
        this.vines.add(vine2);
        this.vines.add(vine3);
        this.vines.add(vine4);
        this.vines.add(vine5);
        this.vines.add(vine6);
        this.vines.add(vine7);
        this.vines.add(vine8);
        this.vines.add(vine9);
        this.vines.add(vine10);
    }
     /**
	* Funcion para obtener una vine de la lista
	* @param ID de la vine que se esta buscando
	* @return la vine con el ID indicado
	*/
    public Vine getVine(Integer ID)
    {
        Vine tmp = null;
        for(int i = 0; i< vines.size(); i++)
        {
            if(vines.get(i).getId()==ID)
            {
                tmp= vines.get(i);
            }
            else
            {
                
            }
        }
        
        return tmp;
    }
     /**
	* Funcion para saber si el jugador ganó para sumarle los points, devolverlo a la posicion inicial y aumentar la velocidad de los enemigos
	*/
    private void won()
    {
        if(player.x<610 && player.y<=160)
        {
            System.out.println("Won!");
            player.won();
            points = points + 1000;
            //for(int i = 0; i< cocodrilos.size();i++)
            //{
            if (kremlins.isEmpty())
            {
            }
            else
            {
                kremlins.get(0).addSpeed();
                vel = kremlins.get(0).getSpeed();
            }
                
            //}

        }
    }
    /**
	* Función para obtener un string del juego
	* @return un string con los points, las vidas, la posicion del jugador enemigos y frutas
	*/
    public String game_str()
    {
        String vidas_str = "LIVES:" + player.getLifes();
        String points_str = "POINTS:" + points;
        won();
        String jugador_str = player.getPosition();
        
        return vidas_str + ";"+ points_str + ";" + jugador_str + ";" + kremlins_str() + ";" + frutas_str();
    }
    /**
	* Función para obtener un string con las posiciones de las frutas y eliminar en caso de colision con el jugador
	* @return un string con las posiciones de las frutas existentes
	*/
    public String frutas_str()
    {
        if(fruits.isEmpty())
        {
            return "0,0";
        }
        else
        {
            String finalStr ="";
            for(int i = 0; i< fruits.size(); i++)
            {
                
                //colision con jugador
                if(fruits.get(i).colision(player.getX(), player.getY()))
                {
                    System.out.println("fruta eliminada");
                    points = points + fruits.get(i).getPoints();
                    String pos = fruits.get(i).getPosition() + ":";
                    finalStr = finalStr + pos;
                    fruits.removeAt(i);

                }
                else
                {
                    
                    String pos = fruits.get(i).getPosition() + ":";
                    finalStr = finalStr + pos;  
                }
                

            }
            return finalStr.substring(0,finalStr.length()-1);
        }
    }

    /**
	* Función para obtener un string con las posiciones de los enemigos despues de moverlos y llamar a la funcion hit() de jugador en caso de colision
	* @return un string con las posiciones de los enemigos existentes
	*/
    public String kremlins_str()
    {
        if(kremlins.isEmpty())
        {
            return "0,0,0";
        }
        else
        {
            String finalStr ="";
            for(int i = 0; i< kremlins.size(); i++)
            {
                //colision con jugador
                if(kremlins.get(i).collision(player.getX(), player.getY()))
                {
                    player.hit();
                }

                //se slane de la pantalla
                if(kremlins.get(i).y>720)
                {
                    
                    
                    System.out.println("coco murio");
                    
                    String pos = kremlins.get(i).getPosition() + ":";
                    finalStr = finalStr + pos;
                    kremlins.get(i).move();
                    kremlins.removeAt(i);
                }
                else
                {
                    
                    String pos = kremlins.get(i).getPosition() + ":";
                    finalStr = finalStr + pos;
                    kremlins.get(i).move();

                }
                
                    
                
            }
            return finalStr.substring(0,finalStr.length()-1);

            }
        
    }

    
}
