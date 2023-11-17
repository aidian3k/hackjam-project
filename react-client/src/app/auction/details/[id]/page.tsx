import { Box, Button, Container, Grid, TextField } from "@mui/material";
import ProductInfo from "@/components/product-info/productInfo";
import BidInput from "@/components/bid-input/bidInput";
import ProductQuestions from "@/components/product-questions/productQuestions";

export default function AuctionDetails({ params }: { params: { id: number } }) {
  return (
    <Container component={"main"}>
      <Grid container spacing={2}>
        <Grid item xs={3}>
          <ProductInfo />
        </Grid>
        <Grid item xs={6}></Grid>
        <Grid item xs={3}></Grid>
      </Grid>
      <BidInput />
      <Box mt={2}>
        <ProductQuestions />
      </Box>
    </Container>
  );
}
