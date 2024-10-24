package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class PlayGame {

    public static final int MINIMUM_VALUE_CAR_POWER = 4;
    InputMessage inputMessage = new InputMessage();
    Map<String, String> carData = new HashMap<>();
    int maxDistance = 0;
    List<String> winners = new ArrayList<>();

    public void racingRaceParticipation(String carName){
        goStartingLine(carName);
    }

    public void goStartingLine(String carName){
        carData.put(carName, "");
    }

    public void racingRaceInProgress(String carName) {
        if (carPower() >= MINIMUM_VALUE_CAR_POWER) {
            moveForward(carName);
        }
    }

    public int carPower(){
        return Randoms.pickNumberInRange(0, 9);
    }

    public String currentDistance(String carName){
        return carData.get(carName);
    }

    public void moveForward(String carName){
        carData.put(carName, currentDistance(carName) + "-");
    }

    public void printRaceResult(String carName) {
        System.out.println(carName + " : " + carData.get(carName));
    }

    public void whoMaxDistance(String carName) {
        if (finalDistanceMeasurement(carName) > maxDistance) {
            maxDistance = finalDistanceMeasurement(carName);
        }
    }

    public int finalDistanceMeasurement(String carName){
        return carData.get(carName).length();
    }

    public void cowinner(String carName) {
        if (finalDistanceMeasurement(carName) == maxDistance) {
            winners.add(carName);  // 우승자 추가
        }
    }

    public void printWinners() {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

    public void startingCarNumber(String[] carNames){
        for (String carName : carNames) {
            racingRaceParticipation(carName);
        }
    }

    public void NumberOfRaces(String tryRacingRaces, String[] carNames){
        for(int i=0; i<Integer.parseInt(tryRacingRaces); i++){
            racingStart(carNames);
        }
    }

    public void racingStart(String[] carNames){
        for (String carName : carNames) {
            racingRaceInProgress(carName);
            printRaceResult(carName);
        }
        System.out.println();
    }

    public void whoWinners(String[] carNames){
        for (String carName : carNames) {
            whoMaxDistance(carName);
            cowinner(carName);
        }
        printWinners();
    }

    public void racingRace(){
        String[] carNames = inputMessage.inputCarNameReturnSplit(",");
        String tryRacingRaces = inputMessage.inputNumberOfTries();

        startingCarNumber(carNames);

        NumberOfRaces(tryRacingRaces, carNames);

        whoWinners(carNames);
    }
}
