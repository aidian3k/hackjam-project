import Image from "next/image";
import { Button, Container } from "@mui/material";

export default function Home() {
  return (
    <Container component={"main"}>
      <Button variant={"contained"}>Click me!</Button>
    </Container>
  );
}
