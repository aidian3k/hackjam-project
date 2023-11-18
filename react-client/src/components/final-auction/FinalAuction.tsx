"use client";

import * as React from "react";
import { ChangeEvent, useEffect, useState } from "react";
import TextField from "@mui/material/TextField";
import { outlinedInputClasses } from "@mui/material/OutlinedInput";
import Box from "@mui/material/Box";
import {
  createTheme,
  Theme,
  ThemeProvider,
  useTheme,
} from "@mui/material/styles";
import Button from "@mui/material/Button";
import { Chip, InputAdornment } from "@mui/material";
import {
  AuctionCreationDto,
  AuctionDetailsDto,
  MediaDto,
  TagDto,
} from "@/types/auctionTypes";
import Typography from "@mui/material/Typography";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import { DesktopDateTimePicker } from "@mui/x-date-pickers/DesktopDateTimePicker";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import { addAuction } from "@/services/auction-service";
import { useRouter } from "next/navigation";

const customTheme = (outerTheme: Theme) =>
  createTheme({
    palette: {
      mode: outerTheme.palette.mode,
    },
    components: {
      MuiTextField: {
        styleOverrides: {
          root: {
            "--TextField-brandBorderColor": "#E0E3E7",
            "--TextField-brandBorderHoverColor": "#B2BAC2",
            "--TextField-brandBorderFocusedColor": "#6F7E8C",
            "& label.Mui-focused": {
              color: "var(--TextField-brandBorderFocusedColor)",
            },
          },
        },
      },
      MuiOutlinedInput: {
        styleOverrides: {
          notchedOutline: {
            borderColor: "var(--TextField-brandBorderColor)",
          },
          root: {
            [`&:hover .${outlinedInputClasses.notchedOutline}`]: {
              borderColor: "var(--TextField-brandBorderHoverColor)",
            },
            [`&.Mui-focused .${outlinedInputClasses.notchedOutline}`]: {
              borderColor: "var(--TextField-brandBorderFocusedColor)",
            },
          },
        },
      },
      MuiFilledInput: {
        styleOverrides: {
          root: {
            "&:before, &:after": {
              borderBottom: "2px solid var(--TextField-brandBorderColor)",
            },
            "&:hover:not(.Mui-disabled, .Mui-error):before": {
              borderBottom: "2px solid var(--TextField-brandBorderHoverColor)",
            },
            "&.Mui-focused:after": {
              borderBottom:
                "2px solid var(--TextField-brandBorderFocusedColor)",
            },
          },
        },
      },
      MuiInput: {
        styleOverrides: {
          root: {
            "&:before": {
              borderBottom: "2px solid var(--TextField-brandBorderColor)",
            },
            "&:hover:not(.Mui-disabled, .Mui-error):before": {
              borderBottom: "2px solid var(--TextField-brandBorderHoverColor)",
            },
            "&.Mui-focused:after": {
              borderBottom:
                "2px solid var(--TextField-brandBorderFocusedColor)",
            },
          },
        },
      },
    },
  });

function FinalAuction({
  auctionDetails,
  media,
}: {
  auctionDetails: AuctionDetailsDto | null;
  media: MediaDto | null;
}) {
  const router = useRouter();
  const outerTheme = useTheme();
  const [price, setPrice] = useState<string>(
    auctionDetails ? auctionDetails.auctionCoreInformation.price.toString() : ""
  );
  const [title, setTitle] = useState(
    auctionDetails ? auctionDetails.auctionCoreInformation.title : ""
  );
  const [description, setDescription] = useState(
    auctionDetails ? auctionDetails.auctionCoreInformation.description : ""
  );
  const [tags, setTags] = useState(
    auctionDetails ? auctionDetails.tagNames : []
  );
  const [tagInput, setTagInput] = useState("");
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);
  const [localization, setLocalization] = useState<string>("");

  const handleStartDateChange = (date: Date | null) => {
    setStartDate(date);
  };

  const handleEndDateChange = (date: Date | null) => {
    setEndDate(date);
  };

  const handleLocalizationChange = (event: ChangeEvent<HTMLInputElement>) => {
    setLocalization(event.target.value);
  };

  const handleAddTag = () => {
    if (tagInput.length === 0) return;
    setTags([...tags, tagInput]);
  };

  const handleDeleteTag = (tag: string) => {
    setTags(tags.filter((t) => t !== tag));
  };

  const handleAddAuction = () => {
    if (!auctionDetails) return;
    if (!startDate || !endDate || !localization || !media) return;
    const data: AuctionCreationDto = {
      auctionCoreInformation: auctionDetails.auctionCoreInformation,
      tags: tags.map((tag) => ({ name: tag } as TagDto)),
      localization: localization,
      startDate: startDate,
      endDate: endDate,
      media: media,
    };

    addAuction(data).then((auction) => {
      router.push("/");
    });
  };

  const handleRemoveAuction = () => {
    router.push("/auctions");
  };

  useEffect(() => {
    if (!auctionDetails) return;
    setPrice(auctionDetails.auctionCoreInformation.price.toString());
    setTitle(auctionDetails.auctionCoreInformation.title);
    setDescription(auctionDetails.auctionCoreInformation.description);
    setTags(auctionDetails.tagNames);
  }, [auctionDetails]);

  return (
    <Box mt={2}>
      <ThemeProvider theme={customTheme(outerTheme)}>
        <Box display={"flex"} flexDirection={"column"} gap={2}>
          <Box display={"flex"} width={"100%"} gap={1}>
            <DesktopDateTimePicker
              sx={{ width: "100%" }}
              label={"Start date"}
              value={startDate}
              onChange={handleStartDateChange}
            />
            <DesktopDateTimePicker
              sx={{ width: "100%" }}
              label={"End date"}
              value={endDate}
              onChange={handleEndDateChange}
            />
          </Box>
          <TextField
            label="Localization"
            type="text"
            value={localization}
            onChange={handleLocalizationChange}
            className="mb-4"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <LocationOnIcon />
                </InputAdornment>
              ),
            }}
          />
          <TextField
            fullWidth
            label="Price"
            value={price}
            type={"number"}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <AttachMoneyIcon />
                </InputAdornment>
              ),
            }}
            onChange={(e) => setPrice(e.target.value)}
          />
          <TextField
            fullWidth
            label="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <TextField
            multiline
            rows={4}
            fullWidth
            label="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          <Box>
            <TextField
              label="Add tag"
              variant="outlined"
              value={tagInput}
              onChange={(e) => setTagInput(e.target.value)}
              onKeyPress={(e) => e.key === "Enter" && handleAddTag()}
            />
            {tags.length !== 0 && (
              <Box mt={2}>
                <Typography variant={"body1"}>Tags:</Typography>
                {tags.map((tag, index) => (
                  <Chip
                    key={index}
                    label={tag}
                    onDelete={() => handleDeleteTag(tag)}
                    style={{ margin: "5px" }}
                  />
                ))}
              </Box>
            )}
          </Box>
        </Box>

        <Box sx={{ my: 2, display: "flex", justifyContent: "center" }}>
          <Button
            variant="contained"
            sx={{
              marginRight: 10,
              backgroundColor: "#64CCC5",
              "&:hover": {
                backgroundColor: "#4FA3A1",
              },
            }}
            onClick={() => handleAddAuction()}
          >
            Add auction
          </Button>
          <Button
            variant="outlined"
            sx={{
              borderColor: "#64CCC5",
              color: "#64CCC5",
              "&:hover": {
                borderColor: "#4FA3A1",
                color: "#4FA3A1",
              },
            }}
            onClick={() => handleRemoveAuction()}
          >
            Decline auction
          </Button>
        </Box>
      </ThemeProvider>
    </Box>
  );
}

export default FinalAuction;
