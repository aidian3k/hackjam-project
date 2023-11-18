"use client";

import {
    Container
} from "@mui/material";
import React, {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {AuctionCard} from "@/components/auction-card/auctionCard";

export type Auction = {
    id: number,
    imageUrl: string,
    title: string,
    description: string,
    tags: string[],
}

const auctionsMock: Auction[] = [
    {
        id: 1,
        imageUrl: "/img/mocks/20231118_095333.jpg",
        title: "Asus VivoBook 15 OLED",
        description: "Experience the future of visual excellence with the Asus VivoBook 15 OLED. Immerse yourself in stunning colors and sharp details on the vibrant OLED display, while enjoying seamless performance powered by cutting-edge technology. The sleek design adds a touch of elegance to your computing experience, making the Asus VivoBook 15 OLED the perfect blend of style and substance. Elevate your work and entertainment—discover the brilliance within each frame.",
        tags: ["AsusVivoBook", "#OLEDDisplay"],
    },
    {
        id: 2,
        imageUrl: "/img/mocks/20231118_095202.jpg",
        title: "Asus VivoBook 15 OLED",
        description: "Experience the future of visual excellence with the Asus VivoBook 15 OLED. Immerse yourself in stunning colors and sharp details on the vibrant OLED display, while enjoying seamless performance powered by cutting-edge technology. The sleek design adds a touch of elegance to your computing experience, making the Asus VivoBook 15 OLED the perfect blend of style and substance. Elevate your work and entertainment—discover the brilliance within each frame.",
        tags: ["AsusVivoBook", "#OLEDDisplay"],
    },
    {
        id: 3,
        imageUrl: "/img/mocks/20231118_095244.jpg",
        title: "Asus VivoBook 15 OLED",
        description: "Experience the future of visual excellence with the Asus VivoBook 15 OLED. Immerse yourself in stunning colors and sharp details on the vibrant OLED display, while enjoying seamless performance powered by cutting-edge technology. The sleek design adds a touch of elegance to your computing experience, making the Asus VivoBook 15 OLED the perfect blend of style and substance. Elevate your work and entertainment—discover the brilliance within each frame.",
        tags: ["AsusVivoBook", "#OLEDDisplay"],
    },
    {
        id: 3,
        imageUrl: "/img/mocks/20231118_072932.jpg",
        title: "Asus VivoBook 15 OLED",
        description: "Experience the future of visual excellence with the Asus VivoBook 15 OLED. Immerse yourself in stunning colors and sharp details on the vibrant OLED display, while enjoying seamless performance powered by cutting-edge technology. The sleek design adds a touch of elegance to your computing experience, making the Asus VivoBook 15 OLED the perfect blend of style and substance. Elevate your work and entertainment—discover the brilliance within each frame.",
        tags: ["AsusVivoBook", "#OLEDDisplay"],
    },
];



export default function Auctions() {
    const [auctions, setAuctions] = useState<Auction[]>([]);

    useEffect(() => {

        const fetchAuctions = async () => {
            const response = await fetch(`http://localhost:8080/api/auctions`);
            const data = await response.json();
            setAuctions(data);
        }

        fetchAuctions();
        // setAuctions(auctionsMock);

    }, []);

    return (
        <Container component={"main"} sx={{ pt: 4 }}  >
            <div className={"grid grid-cols-2 gap-y-3 gap-x-8"}>
                {auctions?.map((auction, index) => (
                <AuctionCard
                    key={index}
                    title={auction.title}
                    description={auction.description}
                    imgUrl={auction.imageUrl}
                    tags={auction.tags}
                />
                ))}
            </div>
        </Container>
    );
}
