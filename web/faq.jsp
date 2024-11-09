<%@include file="user/header.jsp"%>

<!DOCTYPE html><html lang="en"><head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FAQ</title>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body>

        <div id="accordion">
            <h3>What is your return policy?</h3>
            <div>
                <p>
                    We offer a 30-day return policy on all unworn shoes.
                    If you are not satisfied with your purchase, you can return
                    it for a full refund or exchange it for another size or style.
                    Please ensure the shoes are in their original packaging and
                    include the receipt or proof of purchase.
                </p>
            </div>
            <h3>How do I know what size to order?</h3>
            <div>
                <p>
                    We provide a detailed size chart on each product page to
                    help you determine the best fit. If you're unsure, measure
                    your foot and compare it to the chart. If you still have
                    questions, feel free to contact our customer service team for assistance.
                </p>
            </div>
            <h3>Do you offer international shipping?</h3>
            <div>
                <p>
                    Yes, we offer international shipping to many countries.
                    Shipping fees and delivery times vary based on the
                    destination. During checkout, you can see the available
                    shipping options and costs for your location.
                </p>
            </div>
            <h3>How can I track my order?</h3>
            <div>
                <p>
                    Once your order is shipped, you will receive a confirmation
                    email with a tracking number. You can use this number to
                    track your order on our website or the carrier's website.
                    If you have any issues with tracking, please contact our
                    customer service team for help.
                </p>
            </div>
            <h3>What payment methods do you accept?</h3>
            <div>
                <P>
                    We accept a variety of payment methods, including major
                    credit cards (Visa, MasterCard, American Express), PayPal,
                    Apple Pay, and Google Pay. All transactions are secure and
                    encrypted to protect your information.
                </P>
            </div>
        </div>
    </body>
</html>
<%@include file="user/footer.jsp" %>