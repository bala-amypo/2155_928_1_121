@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return roomService.save(room);
    }

    @GetMapping
    public List<ExamRoom> getRooms() {
        return roomService.findAll();
    }
}
