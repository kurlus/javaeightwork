import java.util.List;

public class LamdaExecutor {

    public static void main(String[] args) throws Exception{

        WorkAroundStreams streams = new WorkAroundStreams();
        List<Dish> menu = streams.getMenuDishData();

        MenuQueries queries = new MenuQueries();

    }
}
