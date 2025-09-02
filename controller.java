@RestController
@RequestMapping("/mpesa")
public class MpesaController {

    private final MpesaService mpesaService;

    public MpesaController(MpesaService mpesaService) {
        this.mpesaService = mpesaService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Map<String, Object>> payWithMpesa(@RequestBody Map<String, Object> order) {
        String phone = (String) order.get("phone");
        double amount = Double.parseDouble(order.get("amount").toString());
        String orderDetails = (String) order.get("order");

        Map<String, Object> response = mpesaService.stkPush(phone, amount, orderDetails);

        return ResponseEntity.ok(response);
    }
}
