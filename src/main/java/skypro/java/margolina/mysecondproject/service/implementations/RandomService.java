package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {
    private final Random rand;

    public RandomService(Random random)
    {
        this.rand = random;
    }
    public int getRandomInt(int maxAmount){
        return rand.nextInt(maxAmount);
    }
}
