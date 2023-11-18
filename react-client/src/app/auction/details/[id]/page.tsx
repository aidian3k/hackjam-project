import { Box, Button, Container, Grid, TextField } from "@mui/material";
import ProductInfo from "@/components/product-info/productInfo";
import BidInput from "@/components/bid-input/bidInput";
import ProductQuestions from "@/components/product-questions/productQuestions";
import AuctionInfo from "@/components/auction-info/auctionInfo";
import PersonaLiveComponent from "@/components/persona-live-component/personaLiveComponent";

export default function AuctionDetails({ params }: { params: { id: number } }) {
  const bidders = [
    { name: "Jhon Smith", price: 250 },
    { name: "Lucas Tekk", price: 200 },
    { name: "Monica Rose", price: 120 },
  ];
  return (
    <Container component={"main"}>
      <Grid container spacing={2} pt={4} pb={4}>
        <Grid item xs={3}>
          <ProductInfo />
        </Grid>
        <Grid item xs={6}>
            <PersonaLiveComponent />
        </Grid>
        <Grid item xs={3}>
            <AuctionInfo
                currentPrice={999}
                numberOfBidders={3}
                topBidders={bidders}
                endDate={new Date("2023-12-20T12:01:04.753Z")}
            />
        </Grid>
      </Grid>
      <BidInput />
      <Box mt={2}>
        <ProductQuestions />
      </Box>
    </Container>
  );
}
