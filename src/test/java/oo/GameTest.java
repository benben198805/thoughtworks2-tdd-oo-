package oo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private Game game;
    @Mock
    private ConsolePrinter consolePrinter;
    InOrder inOrder;

    @Before
    public void setUp() {
        inOrder = inOrder(consolePrinter);
        game = new Game(consolePrinter);
    }

    @Test
    public void shouldSecondPlayerLoseWhenFistPlayerIsPowerful() {
        Player firstPlayer = new Player("张三", 10, 10);
        Player secondPlayer = new Player("李四", 9, 10);

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：-1\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldFirstPlayerLoseWhenSecondPlayerIsPowerful() {
        Player firstPlayer = new Player("张三", 10, 8);
        Player secondPlayer = new Player("李四", 20, 9);

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了8点伤害，李四剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了普通人张三，张三受到了9点伤害，张三剩余生命：1\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了8点伤害，李四剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了普通人张三，张三受到了9点伤害，张三剩余生命：-8\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void should_return_first_player_lose_when_first_player_is_normal_and_second_player_is_warrior() {
        Player firstPlayer = new Player("张三", 10, 8);
        Warrior secondPlayer = new Warrior("李四", 20, 9,"战士",0,new Weapon("木棒",1));

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了战士李四，李四受到了8点伤害，李四剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四用木棒攻击了普通人张三，张三受到了10点伤害，张三剩余生命：0\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了战士李四，李四受到了8点伤害，李四剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四用木棒攻击了普通人张三，张三受到了10点伤害，张三剩余生命：-10\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void should_return_first_player_lose_when_first_player_is_warrior_and_second_player_is_warrior() {
        Warrior firstPlayer = new Warrior("张三", 20, 5,"战士",1,new Weapon("木棒",1));
        Warrior secondPlayer = new Warrior("李四", 20, 5,"战士",1,new Weapon("钉耙",10));


        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用木棒攻击了战士李四，李四受到了5点伤害，李四剩余生命：15\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四用钉耙攻击了战士张三，张三受到了14点伤害，张三剩余生命：6\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用木棒攻击了战士李四，李四受到了5点伤害，李四剩余生命：10\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四用钉耙攻击了战士张三，张三受到了14点伤害，张三剩余生命：-8\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }
}