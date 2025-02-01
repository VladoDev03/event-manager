import * as mediaService from "../services/mediaService";
import { useContext, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

export function ImageForm() {
  const navigate = useNavigate();
  const [files, setFiles] = useState(null);
  const { eventId } = useParams();
  const {user} = useContext(AuthContext);

  const submitHandler = async (e) => {
    e.preventDefault();

    if (!files) {
      console.error("No files selected");
      return;
    }

    const formData = new FormData();
    Array.from(files).forEach((file) => {
      formData.append("files", file);
    });

    formData.append("eventId", eventId);
    formData.append("userId", user.userId);

    try {
      const result = await mediaService.uploadMedia(formData);
      console.log("Media URLs:", result);
      navigate("/");
    } catch (error) {
      console.error("Error uploading images:", error.message);
    }
  };

  const pictureHandler = (e) => {
    const selectedFiles = e.target.files;
    if (selectedFiles && selectedFiles.length > 0) {
      setFiles(selectedFiles);
    } else {
      setFiles(null);
    }
  };

  const cancelImage = () => {
    navigate(`../event/${eventId}`);
  };

  return (
    <form
      onSubmit={submitHandler}
      className="mainContainerCreateEvent"
      method="post"
      encType="multipart/form-data"
    >
      <div>
        <input
          id="picture"
          type="file"
          name="picture"
          accept="image/*,video/*"
          multiple
          onChange={pictureHandler}
          required
        />
      </div>
      <div>
        <input type="submit" className="submitBtn" value="Share" />
        <input type="button" onClick={cancelImage} value="Cancel" />
      </div>
    </form>
  );
}
