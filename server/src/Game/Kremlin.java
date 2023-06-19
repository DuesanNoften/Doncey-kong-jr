package Game;
/*
* Clase padre Cocodrilo para crear enemigos a partir de las clases hijas Azul y Rojo
*/
public abstract class Kremlin
{
    static protected Integer speed;
    protected Integer[] vine;
    protected String position;
    protected Integer x;
    protected Integer y;
    /**
    * Función para detectar una colision entre el jugador y el enemigo
    * @param player_x posicion en x del jugador
    * @param player_y posicion en y del jugador
    * @return true si colisiona, false de lo contrario
    */
    public Boolean collision(Integer player_x, Integer player_y)
    {
        if((player_x == this.x) && (((this.y +20)> player_y) && ((this.y -20)< player_y)))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public void move(){}
    /**
    * Función para cambiar el valor de velocidad
    * @param speed la velocidad a la que se quiere cambiar
    */
    public void setSpeed(Integer speed)
    {
        Kremlin.speed = speed;
    }
     /**
    * Función para aumentar el valor de velocidad en un valor de 5
    */
    public void addSpeed()
    {
        speed = speed + 5;
    }
     /**
    * Función para obtener el valor de velocidad
    * @return velocidad
    */
    public Integer getSpeed()
    {
        return speed;
    }
    /**
    * Función para obtener el valor de la posicion del enemigo
    * @return posicion
    */
    public String getPosition()
    {
        return position;
    }
}
