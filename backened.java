@RestController
@RequestMapping("/mpesa")
public class MpesaController {

    private static final String CONSUMER_KEY = "YOUR_CONSUMER_KEY";
    private static final String CONSUMER_SECRET = "YOUR_CONSUMER_SECRET";
    private static final String BUSINESS_SHORTCODE = "174379"; // Replace with your shortcode
    private static final String PASSKEY = "YOUR_PASSKEY";
    private static final String CALLBACK_URL = "https://yourdomain.com/mpesa/callback";

    @PostMapping("/pay")
    public ResponseEntity<?> payWithMpesa(@RequestBody Map<String, Object> payload) {
        String phone = payload.get("phone").toString();
        int amount = ((Number)payload.get("amount")).intValue();

        // Generate timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Generate password
        String password = Base64.getEncoder().encodeToString(
            (BUSINESS_SHORTCODE + PASSKEY + timestamp).getBytes()
        );

        // Build JSON request
        Map<String, Object> stkRequest = Map.of(
            "BusinessShortCode", BUSINESS_SHORTCODE,
            "Password", password,
            "Timestamp", timestamp,
            "TransactionType", "CustomerPayBillOnline",
            "Amount", amount,
            "PartyA", phone,
            "PartyB", BUSINESS_SHORTCODE,
            "PhoneNumber", phone,
            "CallBackURL", CALLBACK_URL,
            "AccountReference", "PapsOrder",
            "TransactionDesc", "Paps Online Shopping Payment"
        );

        // Send request to M-PESA API (simulate here)
        // In real code, you would use RestTemplate or WebClient to call Daraja API
        System.out.println("Sending STK Push request: " + stkRequest);

        // For testing, respond immediately
        return ResponseEntity.ok(Map.of("message", "STK Push sent to " + phone + " for KSh " + amount));
    }
}
