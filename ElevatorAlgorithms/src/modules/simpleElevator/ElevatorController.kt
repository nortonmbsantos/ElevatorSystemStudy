package modules.simpleElevator

class ElevatorController(elevator: Elevator) {
    var elevator : Elevator = elevator

    fun addToOrder(value: Int) {
        if (value != elevator.currentFloor) {
            if (!elevator.floorsToGo.contains(value)){

                when(elevator.elevatorState) {
                    ElevatorState.STOPED -> {
                        elevator.floorsToGo.add(value)

                        if(value>elevator.currentFloor){
                            elevator.elevatorState = ElevatorState.MOVING_UP
                        } else if (value<elevator.currentFloor){
                            elevator.elevatorState = ElevatorState.MOVING_DOWN
                        }

                    }

                    ElevatorState.MOVING_UP -> {
                        // If is the
                        // TODO Check is only going up
                        var indexToPlace = elevator.floorsToGo.size
                        for ( (index, floor) in elevator.floorsToGo.withIndex() ) {
                            if(floor > value){
                                indexToPlace = index
                                break;
                            }
                        }
                        elevator.floorsToGo.add(indexToPlace, value)
                    }

                    ElevatorState.MOVING_DOWN -> {
                        var indexToPlace = elevator.floorsToGo.size - 1
                        for ( (index, floor) in elevator.floorsToGo.withIndex() ) {
                            if(floor < value){
                                indexToPlace = index
                                break;
                            }
                        }
                        elevator.floorsToGo.add(indexToPlace, value)
                    }
                }
            }
        }
    }



    fun move() {
        println("Current floor: " + elevator.currentFloor + " [ " + elevator.elevatorState + " ] to " + elevator.floorsToGo[0] +  " in order: " + elevator.floorsToGo)

        if(elevator.floorsToGo.size > 1){
            if(elevator.floorsToGo[1] > elevator.floorsToGo[0]){
                elevator.elevatorState = ElevatorState.MOVING_UP
            } else if(elevator.floorsToGo[1] < elevator.floorsToGo[0]){
                elevator.elevatorState = ElevatorState.MOVING_DOWN
            }
        } else {
            elevator.elevatorState = ElevatorState.STOPED
        }

        elevator.currentFloor = elevator.floorsToGo[0]
        elevator.floorsToGo.removeFirst()
    }

}