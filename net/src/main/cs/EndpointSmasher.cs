namespace LiquidPoolDash
{
    partial class EndpointSmasher
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            this.progressBar1 = new System.Windows.Forms.ProgressBar();
            this.btnGo = new System.Windows.Forms.Button();
            this.chrtHistogram = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.txtEndpoint = new System.Windows.Forms.TextBox();
            this.lblRate = new System.Windows.Forms.Label();
            this.btnStop = new System.Windows.Forms.Button();
            this.txtResponse = new System.Windows.Forms.TextBox();
            this.lblEndpoint = new System.Windows.Forms.Label();
            this.lblResponse = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.chrtHistogram)).BeginInit();
            this.SuspendLayout();
            // 
            // progressBar1
            // 
            this.progressBar1.Location = new System.Drawing.Point(70, 38);
            this.progressBar1.Name = "progressBar1";
            this.progressBar1.Size = new System.Drawing.Size(718, 13);
            this.progressBar1.TabIndex = 0;
            // 
            // btnGo
            // 
            this.btnGo.Location = new System.Drawing.Point(15, 89);
            this.btnGo.Name = "btnGo";
            this.btnGo.Size = new System.Drawing.Size(134, 39);
            this.btnGo.TabIndex = 2;
            this.btnGo.Text = "Go";
            this.btnGo.UseVisualStyleBackColor = true;
            // 
            // chrtHistogram
            // 
            chartArea1.Name = "ChartArea1";
            this.chrtHistogram.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.chrtHistogram.Legends.Add(legend1);
            this.chrtHistogram.Location = new System.Drawing.Point(155, 68);
            this.chrtHistogram.Name = "chrtHistogram";
            series1.ChartArea = "ChartArea1";
            series1.Legend = "Legend1";
            series1.Name = "Series1";
            this.chrtHistogram.Series.Add(series1);
            this.chrtHistogram.Size = new System.Drawing.Size(633, 300);
            this.chrtHistogram.TabIndex = 3;
            this.chrtHistogram.Text = "chart1";
            // 
            // txtEndpoint
            // 
            this.txtEndpoint.Location = new System.Drawing.Point(70, 12);
            this.txtEndpoint.Name = "txtEndpoint";
            this.txtEndpoint.Size = new System.Drawing.Size(718, 20);
            this.txtEndpoint.TabIndex = 4;
            // 
            // lblRate
            // 
            this.lblRate.AutoSize = true;
            this.lblRate.Location = new System.Drawing.Point(31, 38);
            this.lblRate.Name = "lblRate";
            this.lblRate.Size = new System.Drawing.Size(33, 13);
            this.lblRate.TabIndex = 5;
            this.lblRate.Text = "Rate:";
            // 
            // btnStop
            // 
            this.btnStop.Location = new System.Drawing.Point(15, 134);
            this.btnStop.Name = "btnStop";
            this.btnStop.Size = new System.Drawing.Size(134, 39);
            this.btnStop.TabIndex = 6;
            this.btnStop.Text = "Stop";
            this.btnStop.UseVisualStyleBackColor = true;
            // 
            // txtResponse
            // 
            this.txtResponse.Enabled = false;
            this.txtResponse.Location = new System.Drawing.Point(15, 389);
            this.txtResponse.Multiline = true;
            this.txtResponse.Name = "txtResponse";
            this.txtResponse.Size = new System.Drawing.Size(773, 87);
            this.txtResponse.TabIndex = 7;
            // 
            // lblEndpoint
            // 
            this.lblEndpoint.AutoSize = true;
            this.lblEndpoint.Location = new System.Drawing.Point(12, 15);
            this.lblEndpoint.Name = "lblEndpoint";
            this.lblEndpoint.Size = new System.Drawing.Size(52, 13);
            this.lblEndpoint.TabIndex = 8;
            this.lblEndpoint.Text = "Endpoint:";
            // 
            // lblResponse
            // 
            this.lblResponse.AutoSize = true;
            this.lblResponse.Location = new System.Drawing.Point(12, 373);
            this.lblResponse.Name = "lblResponse";
            this.lblResponse.Size = new System.Drawing.Size(58, 13);
            this.lblResponse.TabIndex = 9;
            this.lblResponse.Text = "Response:";
            // 
            // EndpointSmasher
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 488);
            this.Controls.Add(this.lblResponse);
            this.Controls.Add(this.lblEndpoint);
            this.Controls.Add(this.txtResponse);
            this.Controls.Add(this.btnStop);
            this.Controls.Add(this.lblRate);
            this.Controls.Add(this.txtEndpoint);
            this.Controls.Add(this.chrtHistogram);
            this.Controls.Add(this.btnGo);
            this.Controls.Add(this.progressBar1);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "EndpointSmasher";
            this.Text = "Endpoint Smasher - DZ-Apps";
            ((System.ComponentModel.ISupportInitialize)(this.chrtHistogram)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ProgressBar progressBar1;
        private System.Windows.Forms.Button btnGo;
        private System.Windows.Forms.DataVisualization.Charting.Chart chrtHistogram;
        private System.Windows.Forms.TextBox txtEndpoint;
        private System.Windows.Forms.Label lblRate;
        private System.Windows.Forms.Button btnStop;
        private System.Windows.Forms.TextBox txtResponse;
        private System.Windows.Forms.Label lblEndpoint;
        private System.Windows.Forms.Label lblResponse;
    }
}
