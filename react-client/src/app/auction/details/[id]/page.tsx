import { Container, Grid } from "@mui/material";
import ProductInfo from "@/components/product-info/productInfo";

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
    </Container>
  );
}
