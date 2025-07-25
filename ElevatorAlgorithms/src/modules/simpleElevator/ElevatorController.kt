package modules.simpleElevator

class ElevatorController(elevator: Elevator) {
    var elevator: Elevator = elevator

    fun addToOrder(value: Int) {
        if (value != elevator.currentFloor && !elevator.floorsToGo.contains(value) && (value >= elevator.firstFloor && value <= elevator.lastFloor)) {
            when (elevator.elevatorState) {
                ElevatorState.STOPED -> {
                    elevator.floorsToGo.add(value)

                    if (value > elevator.currentFloor) {
                        elevator.elevatorState = ElevatorState.MOVING_UP
                    } else if (value < elevator.currentFloor) {
                        elevator.elevatorState = ElevatorState.MOVING_DOWN
                    }

                }

                ElevatorState.MOVING_UP -> {
                    if (value >= elevator.firstFloor && value <= elevator.lastFloor) {

                        var indexToPlace = elevator.floorsToGo.size

                        // if is only going up
                        if (elevator.floorsToGo[0] < elevator.floorsToGo[elevator.floorsToGo.size - 1] || elevator.currentFloor == elevator.firstFloor) {

                            if (value > elevator.currentFloor && value < elevator.floorsToGo[elevator.floorsToGo.size - 1]) {
                                for ((index, floor) in elevator.floorsToGo.withIndex()) {
                                    if (value < floor) {
                                        indexToPlace = index
                                        break;
                                    }
                                }
                            }
                        }

                        // if is going up and then down
                        if (elevator.floorsToGo[0] > elevator.floorsToGo[elevator.floorsToGo.size - 1]) {
                            var splitIndex = 0
                            for ((index, floor) in elevator.floorsToGo.withIndex()) {
                                if (index >= 1) {
                                    if (elevator.floorsToGo[index - 1] > floor) {
                                        splitIndex = index
                                    }
                                }
                            }

                            var pair = splitMutableListAtIndex(elevator.floorsToGo, splitIndex)
                            var firstPartArray = pair.first
                            var lastPartArray = pair.second

                            if (value > firstPartArray[firstPartArray.size - 1]) {
                                indexToPlace = firstPartArray.size
                            } else if (value > elevator.currentFloor && value < firstPartArray[firstPartArray.size - 1]) {
                                for ((index, floor) in firstPartArray.withIndex()) {
                                    if (floor > value) {
                                        indexToPlace = index
                                        break;
                                    }
                                }
                            } else if (value < lastPartArray[lastPartArray.size - 1]) {
                                indexToPlace = elevator.floorsToGo.size
                            } else {
                                for ((index, floor) in firstPartArray.reversed().withIndex()) {
                                    if (floor < value) {
                                        indexToPlace = index + firstPartArray.size - 1
                                        break;
                                    }
                                }
                            }

                        }


                        elevator.floorsToGo.add(indexToPlace, value)
                    }
                }

                ElevatorState.MOVING_DOWN -> {

                    var indexToPlace = elevator.floorsToGo.size
                    // TODO adding value wrong when the is a bigger floorgi
                    // if is only going down
                    if (elevator.floorsToGo[0] > elevator.floorsToGo[elevator.floorsToGo.size - 1] || elevator.currentFloor == elevator.lastFloor) {

                        if (value < elevator.currentFloor && value > elevator.floorsToGo[elevator.floorsToGo.size - 1]) {
                            for ((index, floor) in elevator.floorsToGo.withIndex()) {
                                if (value > floor) {
                                    indexToPlace = index
                                    break;
                                }
                            }
                        }
                    }

                    // if is going down and then up
                    if (elevator.floorsToGo[0] < elevator.floorsToGo[elevator.floorsToGo.size - 1]) {
                        var splitIndex = 0
                        for ((index, floor) in elevator.floorsToGo.withIndex()) {
                            if (index >= 1) {
                                if (elevator.floorsToGo[index - 1] < floor) {
                                    splitIndex = index
                                }
                            }
                        }

                        var pair = splitMutableListAtIndex(elevator.floorsToGo, splitIndex)
                        var firstPartArray = pair.first
                        var lastPartArray = pair.second

                        if (value < firstPartArray[firstPartArray.size - 1]) {
                            indexToPlace = firstPartArray.size
                        } else if (value < elevator.currentFloor && value > firstPartArray[firstPartArray.size - 1]) {
                            for ((index, floor) in firstPartArray.withIndex()) {
                                if (floor > value) {
                                    indexToPlace = index
                                    break;
                                }
                            }
                        } else if (value > lastPartArray[lastPartArray.size - 1]) {
                            indexToPlace = elevator.floorsToGo.size
                        } else {
                            for ((index, floor) in firstPartArray.reversed().withIndex()) {
                                if (floor < value) {
                                    indexToPlace = index + firstPartArray.size - 1
                                    break;
                                }
                            }
                        }

                    }
                    elevator.floorsToGo.add(indexToPlace, value)

                }
            }
            setElevatorDirection()
            println("Added " + value + " floor to list, order now: " + elevator.floorsToGo + " -> [" + elevator.elevatorState + "]")
        } else {
            println("Can't add floor number " + value + ", floor non-existent or already selected")
        }

    }


    fun move() {
        setElevatorDirection()
        elevator.currentFloor = elevator.floorsToGo[0]
        elevator.floorsToGo.removeFirst()

        println("Arrived at: " + elevator.currentFloor + " floor, next stop(s): " + elevator.floorsToGo + " -> [" + elevator.elevatorState + "]")
    }

    fun setElevatorDirection(){
        if (elevator.floorsToGo.size > 1) {
            if (elevator.floorsToGo[1] > elevator.floorsToGo[0]) {
                elevator.elevatorState = ElevatorState.MOVING_UP
            } else if (elevator.floorsToGo[1] < elevator.floorsToGo[0]) {
                elevator.elevatorState = ElevatorState.MOVING_DOWN
            }
        } else {
            elevator.elevatorState = ElevatorState.STOPED
        }
    }

    fun <T> splitMutableListAtIndex(
        originalList: MutableList<T>,
        splitIndex: Int
    ): Pair<MutableList<T>, MutableList<T>> {
        require(splitIndex >= 0 && splitIndex <= originalList.size) {
            "Split index must be within the bounds of the list."
        }

        val firstPart = originalList.subList(0, splitIndex).toMutableList()
        val secondPart = originalList.subList(splitIndex, originalList.size).toMutableList()

        return Pair(firstPart, secondPart)
    }
}