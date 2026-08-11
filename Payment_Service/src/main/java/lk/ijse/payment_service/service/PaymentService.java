package lk.ijse.payment_service.service;

import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // POST /api/payments - Create a new payment
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // GET /api/payments/{paymentId} - Get payment details
    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    // GET /api/payments/user/{userId} - Get payments made by a user
    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    // GET /api/payments/reservation/{resId} - Get payment for a specific reservation
    public Optional<Payment> getPaymentByReservationId(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId);
    }

    // GET /api/payments/receipt/{paymentId} - Generate or retrieve a receipt (PDF)
    // This is a placeholder method; actual implementation depends on your PDF generation logic
    public byte[] getReceiptPdf(Long paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isEmpty()) {
            return new byte[0]; // or throw exception
        }

        Payment payment = paymentOpt.get();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add title
            document.add(new Paragraph("Payment Receipt").setBold().setFontSize(18));

            // Add payment details
            document.add(new Paragraph("Payment ID: " + payment.getId()));
            document.add(new Paragraph("Reservation ID: " + payment.getReservationId()));
            document.add(new Paragraph("User ID: " + payment.getUserId()));
            document.add(new Paragraph("Amount: $" + payment.getAmount()));
            document.add(new Paragraph("Payment Method: " + payment.getPaymentMethod()));
            document.add(new Paragraph("Payment Status: " + payment.getPaymentStatus()));
            document.add(new Paragraph("Transaction ID: " + payment.getTransactionId()));
            document.add(new Paragraph("Paid At: " + (payment.getPaidAt() != null ? payment.getPaidAt().toString() : "N/A")));

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
