import pt.isel.canvas.*
import kotlin.math.sin

private const val FRAME_TIME = 30
private const val SPACE_CODE = 32

const val SPRITE_WIDTH = 24  // [pixels in image file]
const val SPRITE_HEIGHT = 16 // [pixels in image file]

// Dimensions of the Arena grid
const val GRID_WIDTH = 20
const val GRID_HEIGHT = 24

// Dimensions of each cell of the Arena grid
const val VIEW_FACTOR = 2 // each cell is VIEW_FACTOR x sprite
const val CELL_WIDTH = VIEW_FACTOR * SPRITE_WIDTH   // [pixels]
const val CELL_HEIGHT = VIEW_FACTOR * SPRITE_HEIGHT  // [pixels]

fun createCanvas() = Canvas(GRID_WIDTH * CELL_WIDTH, GRID_HEIGHT * CELL_HEIGHT, BLACK)

fun Canvas.drawGridLines() {
    (0 ..< width step CELL_WIDTH).forEach { x -> drawLine(x, 0, x, height, WHITE, 1) }
    (0 ..< height step CELL_HEIGHT).forEach { y -> drawLine(0, y, width, y, WHITE, 1) }
}

val array = intArrayOf(20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1)
fun Canvas.drawArray(a: IntArray) {
    erase()

    drawGridLines()
    for (i in 0 until a.size) {
        val arrayx = (i) * CELL_WIDTH  // in pixels
        val arrayh = (a[i]) * CELL_HEIGHT
        drawRect(arrayx, GRID_HEIGHT * CELL_HEIGHT - arrayh, CELL_WIDTH, arrayh, BLACK)
        drawRect(arrayx, GRID_HEIGHT * CELL_HEIGHT - arrayh, CELL_WIDTH, arrayh, if (sorted) GREEN else WHITE, 10)
    }
    drawImage("black-fade.png",0,50,CELL_WIDTH*20,CELL_HEIGHT*24)
}

var isTextVisible = true

fun instructions(arena: Canvas) {
    Thread.sleep(1)
    if (isTextVisible) {
        arena.erase()
        arena.drawArray(array)
    } else {
        arena.drawRect(CELL_WIDTH*6,CELL_HEIGHT*2,CELL_WIDTH*6,CELL_HEIGHT*2, BLACK)
        arena.drawText(CELL_WIDTH * 6, CELL_HEIGHT * 3, "space for sort", WHITE, 40)
        arena.drawText(CELL_WIDTH * 6, CELL_HEIGHT * 4, "r for random", WHITE, 40)
    }
    isTextVisible = !isTextVisible

}
 var sorted = false



    var t = 0.0 // time variable

fun wave(wavearray: IntArray) {
    val newArray = intArrayOf(3,4,5,6,7,8,9,10,10,10,9,8,7,6,5,4,3,3,3,3)
    for (i in wavearray.indices) {
        wavearray[i] = newArray[i]
    }
    sorted = false
}

fun main(){
        onStart {
            val arena = createCanvas()
            arena.drawGridLines()
            arena.drawArray(array)

            arena.onTimeProgress(FRAME_TIME) {
                if (sorted == false) {
                    arena.drawArray(array)
                    
                }
            }
            arena.onKeyPressed { key ->
                if (key.code == ESCAPE_CODE) arena.close()
                if (key.code == SPACE_CODE) {
                    sorted = true
                    bubbleSort(array,0,array.size-1)
                    arena.erase()
                    arena.drawArray(array)
                }
                if (key.code == 82) {
                    sorted = false
                    for (i in array.indices){
                        array[i] = (1..24).shuffled().random()
                    }

                }
                if (key.code == DOWN_CODE){
                    sorted = false
                    for (i in array.indices){
                        array[i] = array[i] -1
                    }
                }
                if (key.code == UP_CODE){
                    sorted = false
                    for (i in array.indices){
                        array[i] = array[i] + 1
                    }
                }
                if (key.code == RIGHT_CODE){
                    sorted = false
                    val temp = array[array.size - 1]
                    for (i in array.size - 1 downTo 1){
                        array[i] = array[i - 1]
                    }
                    array[0] = temp
                }
                if (key.code == LEFT_CODE) {
                    sorted = false
                    val temp = array[0]
                    for (i in 0 until array.size - 1) {
                        array[i] = array[i + 1]
                    }
                    array[array.size - 1] = temp
                }
                if (key.code == 87){
                    wave(array)
                }
            }

            onFinish { }
        }

    }
